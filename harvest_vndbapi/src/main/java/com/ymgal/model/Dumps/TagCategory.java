package com.ymgal.model.Dumps;

import lombok.Data;

/// <summary>
///		Indicates what type the tag is
/// </summary>
public enum TagCategory {
    /// <summary>
    ///		The tag describes the contents of the game
    /// </summary>
    Content("cont"),
    /// <summary>
    ///		The tag describes the sexual contents of the game
    /// </summary>
    Sexual("ero"),
    /// <summary>
    ///		The tag describes the features of the game
    /// </summary>
    Technical("tech");

    private final String tag;

    TagCategory(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}
