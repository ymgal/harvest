package com.ymgal.harvest;

import com.ymgal.harvest.model.ExtensionName;
import com.ymgal.harvest.model.Website;
import com.ymgal.harvest.model.archive.CharacterArchive;
import com.ymgal.harvest.model.archive.GameArchive;
import com.ymgal.harvest.model.archive.OrgArchive;
import com.ymgal.harvest.model.archive.PersonArchive;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/** 核对新版 Harvest 的原生 Jakarta 注解，包括嵌套模型与原始错误消息。 */
class JakartaValidationCompatibilityTest {

    @Test
    void originalHarvestFieldsAndNestedModelsRemainValidated() {
        try (ValidatorFactory factory = Validation.byDefaultProvider().configure()
                .ignoreXmlConfiguration()
                .messageInterpolator(new ParameterMessageInterpolator()).buildValidatorFactory()) {
            GameArchive game = new GameArchive();
            game.setCharacters(List.of(game.new Characters(null, null, 2)));
            game.setReleases(List.of(new GameArchive.Release()));
            game.setStaff(List.of(game.new Staff(null, "", null, null, "")));
            game.setWebsite(List.of(new Website("", "")));
            game.setExtensionName(List.of(new ExtensionName("")));
            CharacterArchive character = new CharacterArchive();
            character.setGender(3);
            PersonArchive person = new PersonArchive();
            person.setGender(-1);

            HarvestResult result = HarvestResult.ok(game, new OrgArchive(), List.of(person), List.of(character));
            Set<ConstraintViolation<HarvestResult>> violations = factory.getValidator().validate(result);
            Set<String> paths = violations.stream().map(v -> v.getPropertyPath().toString()).collect(Collectors.toSet());
            Set<String> messages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());

            assertTrue(paths.containsAll(Set.of("game.name", "game.characters[0].cid",
                    "game.characters[0].position", "game.releases[0].releaseName",
                    "game.staff[0].sid", "game.website[0].link", "game.extensionName[0].name",
                    "org.name", "personList[0].gender", "characterList[0].gender")));
            assertTrue(messages.containsAll(Set.of("名称不能为空", "vndb角色 cid不能为null",
                    "发布名称不能为空", "STAFF vnsid不能为空", "网站链接不能为空", "拓展名本名不能为空")));
        }
    }

    @Test
    void originalOptionalFieldsAndEmptyCollectionsRemainAllowed() {
        try (ValidatorFactory factory = Validation.byDefaultProvider().configure()
                .ignoreXmlConfiguration()
                .messageInterpolator(new ParameterMessageInterpolator()).buildValidatorFactory()) {
            GameArchive game = new GameArchive();
            game.setHaveChinese(false);
            game.setTypeDesc("");
            game.setName("兼容性样例");
            game.setExtensionName(List.of());
            game.setIntroduction("");
            game.setRestricted(false);
            game.setWebsite(List.of());
            game.setCharacters(List.of());
            game.setReleases(List.of());
            game.setStaff(List.of());
            // 开发商和机构允许为空，空集合也不能被升级意外收紧。
            assertTrue(factory.getValidator().validate(HarvestResult.ok(game, null, List.of(), List.of())).isEmpty());
            Set<String> messages = factory.getValidator().validate(HarvestResult.ok(null, null, null, null))
                    .stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());
            assertEquals(Set.of("game 不能为 null", "personList 不能为 null", "characterList 不能为 null"), messages);
        }
    }
}
