package com.ymgal.harvest;

import com.ymgal.harvest.model.archive.OrgArchive;
import com.ymgal.harvest.model.archive.PersonArchive;
import com.ymgal.harvest.model.archive.CharacterArchive;
import com.ymgal.harvest.model.archive.GameArchive;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 包括机构、角色、人物等等的完整游戏档案结果
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HarvestResult extends BaseHarvestResult implements HarvestMetadata {

    //--- archives ---

    @NotNull(message = "game 不能为 null")
    @Valid
    private GameArchive game;

    /**
     * 2025.01.01 optimize
     * If empty, the default value is used
     */
//    @NotNull(message = "org 不能为 null")
    @Valid
    private OrgArchive org;

    @NotNull(message = "personList 不能为 null")
    @Valid
    private List<PersonArchive> personList;

    @NotNull(message = "characterList 不能为 null")
    @Valid
    private List<CharacterArchive> characterList;

    private HarvestResult() {
    }

    public static HarvestResult ok(GameArchive game,
                                   OrgArchive org,
                                   List<PersonArchive> personList,
                                   List<CharacterArchive> characterList) {
        HarvestResult result = new HarvestResult();
        result.game = game;
        result.org = org;
        result.personList = personList;
        result.characterList = characterList;
        return result;
    }

    public static HarvestResult error(LocalDateTime start, Exception error) {
        HarvestResult harvestResult = new HarvestResult();
        harvestResult.setTaskStartTime(start);
        harvestResult.setError(error);
        return harvestResult;
    }
}