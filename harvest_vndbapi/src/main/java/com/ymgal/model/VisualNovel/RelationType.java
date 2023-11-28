package com.ymgal.model.VisualNovel;

import lombok.Data;

/// <summary>
/// Visual Novel Relations
/// </summary>
public enum RelationType {
    /// <summary>
    /// Sequel
    /// </summary>
    Sequel("seq", "seq"),
    /// <summary>
    /// Prequel
    /// </summary>
    Prequel("preq", "preq"),
    /// <summary>
    /// In the same setting
    /// </summary>
    SameSetting("set", "Same Setting"),
    /// <summary>
    /// Is an alternative version
    /// </summary>
    AlternativeVersion("alt", "Alternative Version"),
    /// <summary>
    /// Shares characters
    /// </summary>
    SharesCharacters("char", "Shares Characters"),
    /// <summary>
    /// Is a side story
    /// </summary>
    SideStory("side", "Side Story"),
    /// <summary>
    /// Is a parent story
    /// </summary>
    ParentStory("par", "Parent Story"),
    /// <summary>
    /// Part of the same series
    /// </summary>
    SameSeries("ser", "Same Series"),
    /// <summary>
    /// Fandisc
    /// </summary>
    Fandisc("fan", "fan"),
    /// <summary>
    /// Original Game
    /// </summary>
    OriginalGame("orig", "Original Game");


    private final String relationType;

    private final String desc;

    RelationType(String relationType, String desc) {
        this.relationType = relationType;
        this.desc = desc;
    }

    public String getRelationType() {
        return relationType;
    }

    public String getDesc() {
        return desc;
    }
}