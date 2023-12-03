package com.ymgal.harvest.vndb.model.Character;

import lombok.Data;

/// <summary>
/// Metadata about a particular Trait
/// </summary>
@Data
public class TraitMetadata {
    /// <summary>
    /// Trait ID
    /// </summary>
    private Integer Id;
    /// <summary>
    /// Spoiler level of Trait
    /// </summary>
    private com.ymgal.harvest.vndb.model.Common.SpoilerLevel SpoilerLevel;

}

