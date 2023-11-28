package com.ymgal.harvest;

import com.ymgal.VndbGetMethod;
import com.ymgal.VndbGetMethodByHttp;
import com.ymgal.filter.VndbFilters;
import com.ymgal.harvest.model.ExtensionName;
import com.ymgal.harvest.model.Website;
import com.ymgal.harvest.model.archive.CharacterArchive;
import com.ymgal.harvest.model.archive.GameArchive;
import com.ymgal.harvest.model.archive.OrgArchive;
import com.ymgal.harvest.model.archive.PersonArchive;
import com.ymgal.helper.DateTimeHelper;
import com.ymgal.helper.JsonHelper;
import com.ymgal.helper.TcpHelper;
import com.ymgal.model.Character.Character;
import com.ymgal.model.Character.VoiceActorMetadata;
import com.ymgal.model.Producer.Producer;
import com.ymgal.model.Staff.Staff;
import com.ymgal.model.VisualNovel.VisualNovel;
import com.ymgal.model.VndbResponse;
import com.ymgal.modelhttp.VndbFilter;
import com.ymgal.modelhttp.enums.FilterName;
import com.ymgal.modelhttp.enums.FilterOperator;
import com.ymgal.modelhttp.vo.Vn;

import java.net.InetSocketAddress;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class VndbHarvest extends Harvest {

    private static final String PREFIX = "https://vndb.org/v";

    public VndbHarvest(String gameUrl) {
        super(gameUrl);
    }

    @Override
    protected void validateUrl(String gameUrl) {
        if (gameUrl.startsWith(PREFIX)) return;
        throw new IllegalArgumentException("VNDB address parsing error: " + gameUrl);
    }


    @Override
    protected HarvestResult exec(String gameUrl, InetSocketAddress proxy) {
        //TODO...

        Integer vnid = Integer.parseInt(gameUrl.split(PREFIX)[1]);
        TcpHelper.Login();
        GameArchive gameAcrhive = getGameAcrhive(vnid);
        OrgArchive orgArchive = getOrgArchive(gameAcrhive.getDeveloper());
        List<PersonArchive> personArchiveList = getPersonArchiveList(gameAcrhive.getStaff().stream().map(x -> x.getSid()).toArray(x -> new Integer[x]));
        List<CharacterArchive> characterArchiveList = getCharacterArchiveList(vnid);

        HarvestResult harvestResult = HarvestResult.ok(
                gameAcrhive, orgArchive, personArchiveList, characterArchiveList
        );
        TcpHelper.Loginout();

        System.out.println("HarvestResult：  " + JsonHelper.serialize(harvestResult));

        return harvestResult;
    }

    public static GameArchive getGameAcrhive(Integer vnid) {

        // HTTP查询数据
        VndbFilter vndbFilter = new VndbFilter(FilterName.ID.getFilterName(), FilterOperator.EQ.getOperator(), vnid + "");
        VndbResponse<Vn> responseBody = VndbGetMethodByHttp.GetVisualNovel(vndbFilter);
        if (responseBody == null || responseBody.getItems() == null || responseBody.getItems().size() == 0) {
            return null;
        }

        Vn vn = responseBody.getItems().get(0);

        GameArchive archive = new GameArchive();
        // ID
        archive.setVndbId(Integer.parseInt(vn.getId().replace("v", "")));
        // 标题
        archive.setName(vn.getTitle());

        // 是否有汉化
        if (vn.getLanguages() != null) {
            archive.setHaveChinese(vn.getLanguages().stream().anyMatch(x -> "zh-Hans".equals(x) | "zh-Hant".equals(x)));
        }

        // 图片
        archive.setMainImg(vn.getImage().getUrl());
        // 游戏类型
        // 没有类型描述字段
        archive.setTypeDesc("");
        // 开发商
        // 有多家开发商，取第一家
        if (vn.getDevelopers() != null) {
            archive.setDeveloper(Integer.parseInt(vn.getDevelopers().get(0).getId().replace("p", "")));
        }

        // 发售日期
        archive.setReleaseDate(LocalDate.parse(vn.getReleased()));

        // 扩展名
        if (vn.getTitles() != null) {
            List<ExtensionName> extensionNames = vn.getTitles().stream().map(x -> {
                return new ExtensionName(x.getTitle(), x.getLang());
            }).collect(Collectors.toList());
            archive.setExtensionName(extensionNames);
        }

        //简介
        archive.setIntroduction(vn.getDescription());

        // 角色
        VndbResponse<Character> character_tcp = VndbGetMethod.GetCharacter(VndbFilters.VisualNovel.Equals(vnid).toString());
        if (character_tcp.getItems() != null) {
            List<GameArchive.Characters> characters = character_tcp.getItems().stream().map(x -> {
                return new GameArchive().new Characters(
                        x.getId(),
                        x.getVoiced().stream().filter(v -> Objects.equals(v.getVid(), vnid))
                                .findFirst().map(VoiceActorMetadata::getId).orElse(0),
                        x.getVns().stream().filter(p -> Objects.equals(p[0], vnid)).findFirst().map(m -> (String) m[3]).get().equals("main") ? 1 : 0
                );
            }).collect(Collectors.toList());
            archive.setCharacters(characters);
        }


        // 发售信息， 可能发售了多个平台  国家没有只有语言  LocalDate格式化的问题
        VndbResponse<com.ymgal.model.Release.Release> release_tcp = VndbGetMethod.GetRelease(VndbFilters.VisualNovel.Equals(vnid).toString());
        if (release_tcp.getItems() != null) {
            List<GameArchive.Release> releases = release_tcp.getItems().stream().map(x -> {
                return new GameArchive().new Release(x.getTitle(), x.getWebsite(),
                        x.getPlatforms().stream().collect(Collectors.joining(",")),
                        // TODO 时间格式化问题  LocalDate.parse(x.getReleased()),
                        DateTimeHelper.DateFormatConvert(x.getReleased()),
                        x.getLanguages().stream().collect(Collectors.joining(",")),
                        String.valueOf(x.getMinage()));
            }).collect(Collectors.toList());
            archive.setReleases(releases);
        }


        //Staff
        VndbResponse<VisualNovel> visualNovelVndbResponse = VndbGetMethod.GetVisualNovel(VndbFilters.Id.Equals(vnid).toString());
        if (visualNovelVndbResponse.getItems() != null) {
            VisualNovel vn_tcp = visualNovelVndbResponse.getItems().get(0);
            List<GameArchive.Staff> staff = vn_tcp.getStaff().stream().map(x -> {
                return new GameArchive().new Staff(x.getSid(), x.getName(), x.getNote(), x.getRole());
            }).collect(Collectors.toList());
            archive.setStaff(staff);
        }

        System.out.println("GameArchive：  " + JsonHelper.serialize(archive));
        return archive;
    }

    public static OrgArchive getOrgArchive(Integer orgid) {

        OrgArchive orgArchive = new OrgArchive();

        VndbResponse<Producer> ProducerVndbResponse = VndbGetMethod.GetProducer(VndbFilters.Id.Equals(orgid).toString());
        if (ProducerVndbResponse == null || ProducerVndbResponse.getItems() == null || ProducerVndbResponse.getItems().size() == 0) {
            return null;
        }

        Producer producer = ProducerVndbResponse.getItems().get(0);

        orgArchive.setVndbPid(orgid);
        orgArchive.setOrgName(producer.getName());
        orgArchive.setCountry(producer.getLanguage());
        orgArchive.setIntroduction(producer.getDescription());

        // 网站
        if (producer.getLinks() != null) {
            String title = producer.getLinks().getWikidata() == null ? producer.getLinks().getWikipedia() : producer.getLinks().getWikidata();
            orgArchive.setWebsite(new ArrayList<Website>() {
                {
                    this.add(new Website(title,
                            producer.getLinks().getHomepage()));
                }
            });
        }

        //只有别名
        orgArchive.setExtensionNames(new ArrayList<ExtensionName>() {
            {
                this.add(new ExtensionName(producer.getAliases()));
            }
        });
        System.out.println("orgArchive：  " + JsonHelper.serialize(orgArchive));

        return orgArchive;
    }

    public static List<PersonArchive> getPersonArchiveList(Integer[] staffIds) {
        VndbResponse<Staff> StaffVndbResponse = VndbGetMethod.GetStaff(VndbFilters.Id.Equals(staffIds).toString());
        if (StaffVndbResponse == null || StaffVndbResponse.getItems() == null || StaffVndbResponse.getItems().size() == 0) {
            return null;
        }
        List<Staff> staffList = StaffVndbResponse.getItems();
        List<PersonArchive> personArchiveList = new ArrayList<>();
        for (Staff staff : staffList) {
            PersonArchive personArchive = new PersonArchive();
            personArchive.setVndbSid(staff.getId());
            personArchive.setName(staff.getName());
            if (staff.getAliases() != null) {
                personArchive.setExtensionNames(staff.getAliases().stream().map(a -> {
                    return new ExtensionName((String) a[2] == null ? (String) a[1] : (String) a[2]);
                }).collect(Collectors.toList()));
            }
            personArchive.setIntroduction(staff.getDescription());
            personArchive.setCountry(staff.getLanguage());
            if (staff.getLinks() != null) {
                String title = staff.getLinks().getWikidata() == null ? staff.getLinks().getWikipedia() : staff.getLinks().getWikidata();
                personArchive.setWebsite(new ArrayList<Website>() {{
                    this.add(new Website(title, staff.getLinks().getHomepage()));
                }});
            }
            if (staff.getGender() != null) {
                personArchive.setGender(staff.getGender().equals("m") ? 1 : 0);
            }
            personArchiveList.add(personArchive);
        }
        System.out.println("personArchiveList：  " + JsonHelper.serialize(personArchiveList));
        return personArchiveList;
    }

    public static List<CharacterArchive> getCharacterArchiveList(Integer vnid) {
        VndbResponse<Character> CharacterVndbResponse = VndbGetMethod.GetCharacter(VndbFilters.VisualNovel.Equals(vnid).toString());
        if (CharacterVndbResponse == null || CharacterVndbResponse.getItems() == null || CharacterVndbResponse.getItems().size() == 0) {
            return null;
        }
        List<Character> characterList = CharacterVndbResponse.getItems();
        List<CharacterArchive> characterArchiveList = new ArrayList<>();
        for (Character character : characterList) {
            CharacterArchive characterArchive = new CharacterArchive();
            characterArchive.setVndbCid(character.getId());
            characterArchive.setName(character.getName());

            if (character.getAliases() != null) {
                String[] aliaseList = character.getAliases().split("\n");
                characterArchive.setExtensionNames(Arrays.stream(aliaseList).map(x -> new ExtensionName(x)).collect(Collectors.toList()));
            }

            characterArchive.setIntroduction(character.getDescription());
            if (character.getBirthday() != null && character.getBirthday().get(0) != null && character.getBirthday().get(1) != null) {
                characterArchive.setBirthday(LocalDate.of(3000, character.getBirthday().get(1), character.getBirthday().get(0)));
            }
            characterArchive.setMainImg(character.getImage());
            if (character.getGender() != null) {
                characterArchive.setGender(character.getGender().equals("m") ? 1 : 0);
            }
            characterArchiveList.add(characterArchive);
        }
        System.out.println("characterArchives：  " + JsonHelper.serialize(characterArchiveList));
        return characterArchiveList;
    }


}
