package com.ymgal.model.VisualNovel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ymgal.model.Common.SpoilerLevel;
import lombok.Data;

import java.util.ArrayList;

/// <summary>
/// Tag Metadata
/// </summary>
@Data
public class TagMetadata {

    /// <summary>
    /// Tag Id
    /// </summary>
    private Integer Id;
    /// <summary>
    /// Tag Score
    /// </summary>
    private Double Score;
    /// <summary>
    /// Tag Spoiler Level
    /// </summary>
    @JsonProperty("spoiler")
    private SpoilerLevel SpoilerLevel;

    public TagMetadata(ArrayList array) {
        this.Id = (Integer) array.get(0);
        this.Score = (Double) array.get(1);
        this.SpoilerLevel = (SpoilerLevel) array.get(2);
    }

}
