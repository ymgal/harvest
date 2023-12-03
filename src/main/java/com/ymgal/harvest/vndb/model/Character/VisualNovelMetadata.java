package com.ymgal.harvest.vndb.model.Character;

import lombok.Data;

/// <summary>
/// Metadata about a VN
/// </summary>
@Data
public class VisualNovelMetadata {
    /// <summary>
    /// Visual Novel ID
    /// </summary>
    private Integer Id;
    /// <summary>
    /// Visual Novel Release ID
    /// </summary>
    private Integer ReleaseId;
    /// <summary>
    /// Visual Novel Spoiler Level
    /// </summary>
    private com.ymgal.harvest.vndb.model.Common.SpoilerLevel SpoilerLevel;
    /// <summary>
    /// Character Role
    /// </summary>
    private CharacterRole Role;
}

