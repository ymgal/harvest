package com.ymgal.model.Character;

import com.ymgal.model.Common.SpoilerLevel;
import lombok.Data;

/// <summary>
/// List of instances of a character
/// </summary>
@Data
public class CharacterInstances {
    /// <summary>
    /// Character Id
    /// </summary>
    private Integer id;
    /// <summary>
    /// Spoiler level
    /// </summary>
    private SpoilerLevel spoiler;
    /// <summary>
    /// Character's Name
    /// </summary>
    private String name;
    /// <summary>
    /// Character's Original/Japanese Name
    /// </summary>
    private String original;
}

