package com.ymgal.model.Character;

import com.ymgal.model.Common.SpoilerLevel;
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
    private SpoilerLevel SpoilerLevel;
    /// <summary>
    /// Character Role
    /// </summary>
    private CharacterRole Role;
}

