package com.ymgal.harvest.utils;

import com.ymgal.harvest.model.ExtensionName;
import com.ymgal.harvest.model.Website;
import com.ymgal.harvest.model.archive.CharacterArchive;
import com.ymgal.harvest.model.archive.GameArchive;
import com.ymgal.harvest.model.archive.OrgArchive;
import com.ymgal.harvest.model.archive.PersonArchive;
import com.ymgal.harvest.vndb.helper.DateTimeHelper;
import com.ymgal.harvest.vndb.model.Character.Character;
import com.ymgal.harvest.vndb.model.Character.VoiceActorMetadata;
import com.ymgal.harvest.vndb.model.Producer.Producer;
import com.ymgal.harvest.vndb.model.Release.Release;
import com.ymgal.harvest.vndb.model.Staff.Staff;
import com.ymgal.harvest.vndb.model.VisualNovel.VisualNovel;
import com.ymgal.harvest.vndb.modelhttp.vo.Vn;
import com.ymgal.harvest.vndb.modelhttp.vo.common.Exlink;
import org.jsoup.internal.StringUtil;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * VNDB数据转换器
 * 负责将VNDB的数据模型转换为系统内部的Archive模型
 */
public class VndbConverter {

    /**
     * 转换游戏数据
     *
     * @param vn          HTTP查询的VN数据
     * @param characters  TCP查询的角色数据（可选）
     * @param releases    TCP查询的发售信息（可选）
     * @param visualNovel TCP查询的VN详细信息（可选）
     * @param links       HTML解析的链接数据（可选）
     * @return GameArchive
     */
    public GameArchive toGameArchive(Vn vn,
                                     List<Character> characters,
                                     List<Release> releases,
                                     VisualNovel visualNovel,
                                     List<Exlink> links) {
        if (vn == null) {
            return null;
        }

        GameArchive archive = new GameArchive();

        // 初始化空集合
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
            archive.setHaveChinese(vn.getLanguages().stream()
                    .anyMatch(x -> "zh-Hans".equals(x) || "zh-Hant".equals(x)));
        }

        // 网站链接
        if (links != null) {
            List<Website> websites = links.stream()
                    .map(x -> new Website(x.getName(), x.getUrl()))
                    .collect(toList());
            archive.setWebsite(websites);
        }

        // 图片
        if (vn.getImage() != null && vn.getImage().getUrl() != null
                && vn.getImage().getUrl().startsWith("http")) {
            archive.setMainImg(vn.getImage().getUrl());
        }

        // 游戏类型
        archive.setTypeDesc("");

        // 开发商（取第一家）
        if (vn.getDevelopers() != null && !vn.getDevelopers().isEmpty()) {
            archive.setDeveloper(Integer.parseInt(
                    vn.getDevelopers().get(0).getId().replace("p", "")));
        }

        // 发售日期
        String releaseDate = vn.getReleased();
        archive.setReleaseDate(HarvestUtil.validDate(releaseDate));

        // 扩展名
        if (vn.getTitles() != null) {
            List<ExtensionName> extensionNames = vn.getTitles().stream()
                    .filter(x -> x.getTitle() != null && x.getTitle().trim().length() > 0)
                    .filter(x -> !Objects.equals(x.getTitle(), archive.getName()))
                    .map(x -> new ExtensionName(x.getTitle(), x.getLang()))
                    .collect(toList());
            archive.setExtensionName(extensionNames);
        }

        // 简介
        archive.setIntroduction(vn.getDescription() == null ? "" : vn.getDescription());

        // 角色
        if (characters != null && !characters.isEmpty()) {
            int vnid = archive.getVndbId();
            List<GameArchive.Characters> charactersList = characters.stream()
                    .map(x -> new GameArchive().new Characters(
                            x.getId(),
                            x.getVoiced().stream()
                                    .filter(v -> Objects.equals(v.getVid(), vnid))
                                    .findFirst()
                                    .map(VoiceActorMetadata::getId)
                                    .orElse(0),
                            x.getVns().stream()
                                    .filter(p -> Objects.equals(p[0], vnid))
                                    .findFirst()
                                    .map(m -> (String) m[3])
                                    .get()
                                    .equals("main") ? 1 : 0
                    ))
                    .collect(toList());
            archive.setCharacters(charactersList);
        }

        // 发售信息
        if (releases != null && !releases.isEmpty()) {
            List<GameArchive.Release> releaseList = releases.stream()
                    .map(x -> {
                        GameArchive.Release release = new GameArchive.Release();
                        release.setReleaseName(x.getTitle());
                        release.setRelatedLink(x.getWebsite());
                        release.setPlatform(x.getPlatforms().stream()
                                .collect(Collectors.joining(",")));
                        release.setReleaseDate(DateTimeHelper.DateFormatConvert(x.getReleased()));
                        release.setCountry(x.getLanguages().stream()
                                .collect(Collectors.joining(",")));
                        release.setRestrictionLevel(String.valueOf(x.getMinage()));
                        return release;
                    })
                    .collect(toList());

            // 移除无效数据
            releaseList.removeIf(r -> StringUtil.isBlank(r.getReleaseName())
                    || StringUtil.isBlank(r.getPlatform())
                    || StringUtil.isBlank(r.getCountry())
                    || StringUtil.isBlank(r.getRestrictionLevel()));

            archive.setReleases(releaseList);

            // 是否受限制
            archive.setRestricted(releases.stream()
                    .map(Release::getMinage)
                    .filter(Objects::nonNull)
                    .anyMatch(x -> x > 0));
        }

        // Staff
        if (visualNovel != null && visualNovel.getStaff() != null) {
            List<GameArchive.Staff> staff = visualNovel.getStaff().stream()
                    .map(x -> new GameArchive().new Staff(
                            x.getSid(), x.realName(), x.getNote(), x.getRole()))
                    .collect(toList());
            archive.setStaff(staff);
        }

        return archive;
    }

    /**
     * 转换组织/开发商数据
     *
     * @param producer VNDB的Producer数据
     * @return OrgArchive
     */
    public OrgArchive toOrgArchive(Producer producer) {
        if (producer == null) {
            return null;
        }

        OrgArchive orgArchive = new OrgArchive();

        orgArchive.setVndbPid(producer.getId());
        orgArchive.setOrgName(producer.realName());
        orgArchive.setCountry(producer.getLanguage());
        orgArchive.setIntroduction(producer.getDescription() == null ? "" : producer.getDescription());

        // 网站
        if (producer.getLinks() != null) {
            String title = producer.getLinks().getWikidata() == null
                    ? producer.getLinks().getWikipedia()
                    : producer.getLinks().getWikidata();
            orgArchive.setWebsite(new ArrayList<Website>() {{
                if (title != null && producer.getLinks() != null
                        && producer.getLinks().getHomepage() != null) {
                    this.add(new Website(title, producer.getLinks().getHomepage()));
                }
            }});
        }

        // 别名
        orgArchive.setExtensionNames(Collections.emptyList());
        if (producer.getAliases() != null && producer.getAliases().trim().length() > 0) {
            orgArchive.setExtensionNames(
                    Collections.singletonList(new ExtensionName(producer.getAliases())));
        }

        return orgArchive;
    }

    /**
     * 转换单个人员数据
     *
     * @param staff VNDB的Staff数据
     * @return PersonArchive
     */
    public PersonArchive toPersonArchive(Staff staff) {
        if (staff == null) {
            return null;
        }

        PersonArchive personArchive = new PersonArchive();
        personArchive.setVndbSid(staff.getId());
        personArchive.setName(staff.realName());
        personArchive.setExtensionNames(new ArrayList<>());

        // 别名
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

        // 网站
        if (staff.getLinks() != null) {
            String title = staff.getLinks().getWikidata() == null
                    ? staff.getLinks().getWikipedia()
                    : staff.getLinks().getWikidata();
            personArchive.setWebsite(new ArrayList<Website>() {{
                if (title != null && staff.getLinks() != null
                        && staff.getLinks().getHomepage() != null) {
                    this.add(new Website(title, staff.getLinks().getHomepage()));
                }
            }});
        }

        // 性别：默认0未知 1男 2女
        personArchive.setGender(0);
        if (staff.getGender() != null) {
            if (staff.getGender().equals("m")) {
                personArchive.setGender(1);
            } else if (staff.getGender().equals("f")) {
                personArchive.setGender(2);
            }
        }

        return personArchive;
    }

    /**
     * 批量转换人员数据
     *
     * @param staffList VNDB的Staff数据列表
     * @return PersonArchive列表
     */
    public List<PersonArchive> toPersonArchiveList(List<Staff> staffList) {
        if (staffList == null || staffList.isEmpty()) {
            return Collections.emptyList();
        }

        return staffList.stream()
                .map(this::toPersonArchive)
                .filter(Objects::nonNull)
                .collect(toList());
    }

    /**
     * 转换单个角色数据
     *
     * @param character VNDB的Character数据
     * @return CharacterArchive
     */
    public CharacterArchive toCharacterArchive(Character character) {
        if (character == null) {
            return null;
        }

        CharacterArchive characterArchive = new CharacterArchive();
        characterArchive.setVndbCid(character.getId());
        characterArchive.setName(character.realName());

        // 扩展名（别名）
        characterArchive.setExtensionNames(new ArrayList<>());
        if (character.getAliases() != null) {
            String[] aliaseList = character.getAliases().split("\n");
            List<ExtensionName> names = Arrays.stream(aliaseList)
                    .filter(x -> x != null && x.trim().length() > 0)
                    .map(ExtensionName::new)
                    .collect(toList());
            characterArchive.setExtensionNames(names);
        }

        // 简介（目前一堆无意义的英文，直接去掉）
        characterArchive.setIntroduction("");

        // 生日
        if (character.getBirthday() != null
                && character.getBirthday().get(0) != null
                && character.getBirthday().get(1) != null) {
            String dateStr = "3000-" + character.getBirthday().get(1)
                    + "-" + character.getBirthday().get(0);
            characterArchive.setBirthday(HarvestUtil.validDate(dateStr));
        }

        characterArchive.setMainImg(character.getImage());

        // 性别：默认0未知 1男 2女
        characterArchive.setGender(0);
        if (character.getGender() != null) {
            if (character.getGender().equals("m")) {
                characterArchive.setGender(1);
            } else if (character.getGender().equals("f")) {
                characterArchive.setGender(2);
            }
        }

        return characterArchive;
    }

    /**
     * 批量转换角色数据
     *
     * @param characterList VNDB的Character数据列表
     * @return CharacterArchive列表
     */
    public List<CharacterArchive> toCharacterArchiveList(List<Character> characterList) {
        if (characterList == null || characterList.isEmpty()) {
            return Collections.emptyList();
        }

        return characterList.stream()
                .map(this::toCharacterArchive)
                .filter(Objects::nonNull)
                .collect(toList());
    }
}