package com.ymgal.harvest;

import com.ymgal.harvest.model.archive.OrgArchive;
import com.ymgal.harvest.model.archive.PersonArchive;
import com.ymgal.harvest.utils.HarvestUtil;
import com.ymgal.harvest.vndb.VndbGetMethodByHttp;
import com.ymgal.harvest.vndb.helper.DateTimeHelper;
import com.ymgal.harvest.vndb.helper.TcpHelper;
import com.ymgal.harvest.vndb.model.Release.Release;
import com.ymgal.harvest.vndb.model.Staff.Staff;
import com.ymgal.harvest.vndb.modelhttp.VndbFilter;
import com.ymgal.harvest.vndb.modelhttp.vo.Vn;
import com.ymgal.harvest.vndb.modelhttp.vo.common.Exlink;
import com.ymgal.harvest.vndb.VndbGetMethod;
import com.ymgal.harvest.vndb.filter.VndbFilters;
import com.ymgal.harvest.model.ExtensionName;
import com.ymgal.harvest.model.Website;
import com.ymgal.harvest.model.archive.CharacterArchive;
import com.ymgal.harvest.model.archive.GameArchive;
import com.ymgal.harvest.vndb.model.Character.Character;
import com.ymgal.harvest.vndb.model.Character.VoiceActorMetadata;
import com.ymgal.harvest.vndb.model.Producer.Producer;
import com.ymgal.harvest.vndb.model.VisualNovel.VisualNovel;
import com.ymgal.harvest.vndb.model.VndbResponse;
import com.ymgal.harvest.vndb.modelhttp.enums.FilterName;
import com.ymgal.harvest.vndb.modelhttp.enums.FilterOperator;
import org.jsoup.internal.StringUtil;

import java.net.InetSocketAddress;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class VndbHarvest extends Harvest {

    private final String PREFIX = "https://vndb.org/v";

    private final TcpHelper tcpHelper = new TcpHelper();

    private final VndbGetMethod vndbGetMethod = new VndbGetMethod(tcpHelper);

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

        Integer vnid = Integer.parseInt(gameUrl.split(PREFIX)[1]);
        tcpHelper.login();
        GameArchive gameAcrhive = getGameAcrhive(vnid);
        OrgArchive orgArchive = getOrgArchive(gameAcrhive.getDeveloper());
        List<PersonArchive> personArchiveList = getPersonArchiveList(gameAcrhive.getStaff().stream().map(x -> x.getSid()).toArray(x -> new Integer[x]));
        List<CharacterArchive> characterArchiveList = getCharacterArchiveList(vnid);

        HarvestResult harvestResult = HarvestResult.ok(
                gameAcrhive, orgArchive, personArchiveList, characterArchiveList
        );
        tcpHelper.logout();

        return harvestResult;
    }

    public GameArchive getGameAcrhive(Integer vnid) {

        // HTTP查询数据
        VndbFilter vndbFilter = new VndbFilter(FilterName.ID.getFilterName(), FilterOperator.EQ.getOperator(), vnid + "");
        VndbResponse<Vn> responseBody = VndbGetMethodByHttp.GetVisualNovel(vndbFilter);
        if (responseBody == null || responseBody.getItems() == null || responseBody.getItems().size() == 0) {
            return null;
        }

        Vn vn = responseBody.getItems().get(0);

        GameArchive archive = new GameArchive();

        archive.setExtensionName(Collections.emptyList());
        archive.setReleases(Collections.emptyList());
        archive.setRestricted(true);
        archive.setCharacters(Collections.emptyList());
        archive.setStaff(Collections.emptyList());

        // ID
        archive.setVndbId(Integer.parseInt(vn.getId().replace("v", "")));

        // 标题
        archive.setName(vn.getAlttitle());
        if (archive.getName() == null || archive.getName().isEmpty()) {
            archive.setName(vn.getTitle());
        }

        // 是否有汉化
        if (vn.getLanguages() != null) {
            archive.setHaveChinese(vn.getLanguages().stream().anyMatch(x -> "zh-Hans".equals(x) | "zh-Hant".equals(x)));
        }

        // 获取Links
        List<Exlink> linksByHtml = VndbGetMethodByHttp.getLinksByHtml(PREFIX + vnid);
        List<Website> websites = linksByHtml.stream()
                .map(x -> new Website(x.getName(), x.getUrl()))
                .collect(toList());

        archive.setWebsite(websites);

        // 图片
        if (vn.getImage() != null && vn.getImage().getUrl() != null && vn.getImage().getUrl().startsWith("http"))
            archive.setMainImg(vn.getImage().getUrl());

        // 游戏类型
        // 没有类型描述字段
        archive.setTypeDesc("");
        // 开发商
        // 有多家开发商，取第一家
        if (vn.getDevelopers() != null && !vn.getDevelopers().isEmpty()) {
            archive.setDeveloper(Integer.parseInt(vn.getDevelopers().get(0).getId().replace("p", "")));
        }

        // 发售日期, TBA为未知，不设置
        String releaseDate = vn.getReleased();
        archive.setReleaseDate(HarvestUtil.validDate(releaseDate));

        // 扩展名
        if (vn.getTitles() != null) {
            List<ExtensionName> extensionNames = vn.getTitles().stream()
                    .filter(x -> x.getTitle() != null && x.getTitle().trim().length() > 0)
                    .filter(x -> !Objects.equals(x.getTitle(), archive.getName()))//不等于原名
                    .map(x -> new ExtensionName(x.getTitle(), x.getLang()))
                    .collect(toList());
            archive.setExtensionName(extensionNames);
        }

        //简介
        archive.setIntroduction(vn.getDescription() == null ? "" : vn.getDescription());

        // 角色
        VndbResponse<Character> character_tcp = vndbGetMethod.GetCharacter(VndbFilters.VisualNovel.Equals(vnid).toString());
        if (character_tcp != null && character_tcp.getItems() != null) {
            List<GameArchive.Characters> characters = character_tcp.getItems().stream().map(x -> {
                return new GameArchive().new Characters(
                        x.getId(),
                        x.getVoiced().stream().filter(v -> Objects.equals(v.getVid(), vnid))
                                .findFirst().map(VoiceActorMetadata::getId).orElse(0),
                        x.getVns().stream().filter(p -> Objects.equals(p[0], vnid)).findFirst().map(m -> (String) m[3]).get().equals("main") ? 1 : 0
                );
            }).collect(toList());
            archive.setCharacters(characters);
        }


        // 发售信息， 可能发售了多个平台  国家没有只有语言  LocalDate格式化的问题
        VndbResponse<Release> release_tcp = vndbGetMethod.GetRelease(VndbFilters.VisualNovel.Equals(vnid).toString());
        if (release_tcp != null && release_tcp.getItems() != null) {
            List<GameArchive.Release> releases = release_tcp.getItems().stream()
                    .map(x -> {
                        GameArchive.Release release = new GameArchive.Release();
                        release.setReleaseName(x.getTitle());
                        release.setRelatedLink(x.getWebsite());
                        release.setPlatform(x.getPlatforms().stream().collect(Collectors.joining(",")));
                        // TODO 时间格式化问题  LocalDate.parse(x.getReleased()),
                        release.setReleaseDate(DateTimeHelper.DateFormatConvert(x.getReleased()));
                        release.setCountry(x.getLanguages().stream().collect(Collectors.joining(",")));
                        release.setRestrictionLevel(String.valueOf(x.getMinage()));

                        return release;
                    }).collect(toList());

            releases.removeIf(r -> StringUtil.isBlank(r.getReleaseName())
                    || StringUtil.isBlank(r.getPlatform())
                    || StringUtil.isBlank(r.getCountry())
                    || StringUtil.isBlank(r.getRestrictionLevel())
            );

            archive.setReleases(releases);

            //是否受限制
            archive.setRestricted(release_tcp.getItems().stream().map(x -> x.getMinage()).filter(Objects::nonNull).anyMatch(x -> x > 0));
        }

        //Staff
        VndbResponse<VisualNovel> visualNovelVndbResponse = vndbGetMethod.GetVisualNovel(VndbFilters.Id.Equals(vnid).toString());
        if (visualNovelVndbResponse != null && visualNovelVndbResponse.getItems() != null) {
            VisualNovel vn_tcp = visualNovelVndbResponse.getItems().get(0);
            List<GameArchive.Staff> staff = vn_tcp.getStaff().stream().map(x ->
                    new GameArchive().new Staff(x.getSid(), x.realName(), x.getNote(), x.getRole())
            ).collect(toList());
            archive.setStaff(staff);
        }

        return archive;
    }

    public OrgArchive getOrgArchive(Integer orgid) {

        OrgArchive orgArchive = new OrgArchive();

        VndbResponse<Producer> ProducerVndbResponse = vndbGetMethod.GetProducer(VndbFilters.Id.Equals(orgid).toString());
        if (ProducerVndbResponse == null || ProducerVndbResponse.getItems() == null || ProducerVndbResponse.getItems().size() == 0) {
            return null;
        }

        Producer producer = ProducerVndbResponse.getItems().get(0);

        orgArchive.setVndbPid(orgid);
        orgArchive.setOrgName(producer.realName());
        orgArchive.setCountry(producer.getLanguage());

        orgArchive.setIntroduction(producer.getDescription() == null ? "" : producer.getDescription());


        // 网站
        if (producer.getLinks() != null) {
            String title = producer.getLinks().getWikidata() == null ? producer.getLinks().getWikipedia() : producer.getLinks().getWikidata();
            orgArchive.setWebsite(new ArrayList<Website>() {
                {
                    if (title != null && producer.getLinks() != null && producer.getLinks().getHomepage() != null) {
                        this.add(new Website(title,
                                producer.getLinks().getHomepage()));
                    }
                }
            });
        }

        //只有别名
        orgArchive.setExtensionNames(Collections.emptyList());
        if (producer.getAliases() != null && producer.getAliases().trim().length() > 0) {
            orgArchive.setExtensionNames(Collections.singletonList(new ExtensionName(producer.getAliases())));
        }

        return orgArchive;
    }

    public List<PersonArchive> getPersonArchiveList(Integer[] staffIds) {
        VndbResponse<Staff> StaffVndbResponse = vndbGetMethod.GetStaff(VndbFilters.Id.Equals(staffIds).toString());
        if (StaffVndbResponse == null || StaffVndbResponse.getItems() == null || StaffVndbResponse.getItems().size() == 0) {
            return Collections.emptyList();
        }

        List<Staff> staffList = StaffVndbResponse.getItems();
        List<PersonArchive> personArchiveList = new ArrayList<>();
        for (Staff staff : staffList) {
            PersonArchive personArchive = new PersonArchive();
            personArchive.setVndbSid(staff.getId());
            personArchive.setName(staff.realName());
            personArchive.setExtensionNames(new ArrayList<>());

            if (staff.getAliases() != null) {
                List<ExtensionName> names = staff.getAliases()
                        .stream()
                        .map(a -> (String) (a[2] == null ? a[1] : a[2]))
                        .filter(name -> name != null && name.trim().length() > 0)
                        .map(ExtensionName::new)
                        .collect(toList());
                personArchive.setExtensionNames(names);
            }

            personArchive.setIntroduction(staff.getDescription() == null ? "" : staff.getDescription());

            personArchive.setCountry(staff.getLanguage());
            if (staff.getLinks() != null) {
                String title = staff.getLinks().getWikidata() == null ? staff.getLinks().getWikipedia() : staff.getLinks().getWikidata();
                personArchive.setWebsite(new ArrayList<Website>() {{
                    if (title != null && staff.getLinks() != null && staff.getLinks().getHomepage() != null) {
                        this.add(new Website(title, staff.getLinks().getHomepage()));
                    }
                }});
            }
            //默认0未知 1男 2女
            personArchive.setGender(0);
            if (staff.getGender() != null) {
                if (staff.getGender().equals("m")) {
                    personArchive.setGender(1);
                } else if (staff.getGender().equals("f")) {
                    personArchive.setGender(2);
                }
            }
            personArchiveList.add(personArchive);
        }

        return personArchiveList;
    }

    public List<CharacterArchive> getCharacterArchiveList(Integer vnid) {
        VndbResponse<Character> CharacterVndbResponse = vndbGetMethod.GetCharacter(VndbFilters.VisualNovel.Equals(vnid).toString());
        if (CharacterVndbResponse == null || CharacterVndbResponse.getItems() == null || CharacterVndbResponse.getItems().size() == 0) {
            return Collections.emptyList();
        }

        List<Character> characterList = CharacterVndbResponse.getItems();
        List<CharacterArchive> characterArchiveList = new ArrayList<>();
        for (Character character : characterList) {
            CharacterArchive characterArchive = new CharacterArchive();
            characterArchive.setVndbCid(character.getId());
            characterArchive.setName(character.realName());

            // 扩展名
            characterArchive.setExtensionNames(new ArrayList<>());
            if (character.getAliases() != null) {
                String[] aliaseList = character.getAliases().split("\n");

                List<ExtensionName> names = Arrays.stream(aliaseList)
                        .filter(x -> x != null && x.trim().length() > 0)
                        .map(x -> new ExtensionName(x))
                        .collect(toList());
                characterArchive.setExtensionNames(names);
            }

            characterArchive.setIntroduction(character.getDescription() == null ? "" : character.getDescription());

            //目前一堆无意义的英文，还是直接去掉吧
            characterArchive.setIntroduction("");

            if (character.getBirthday() != null && character.getBirthday().get(0) != null && character.getBirthday().get(1) != null) {
                String dateStr = "3000-" + character.getBirthday().get(1) + "-" + character.getBirthday().get(0);
                characterArchive.setBirthday(HarvestUtil.validDate(dateStr));
            }
            characterArchive.setMainImg(character.getImage());

            //默认0未知 1男 2女
            characterArchive.setGender(0);
            if (character.getGender() != null) {
                if (character.getGender().equals("m")) {
                    characterArchive.setGender(1);
                } else if (character.getGender().equals("f")) {
                    characterArchive.setGender(2);
                }
            }
            characterArchiveList.add(characterArchive);
        }
        return characterArchiveList;
    }

}
