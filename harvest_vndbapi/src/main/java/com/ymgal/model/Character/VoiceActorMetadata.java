package com.ymgal.model.Character;

import lombok.Data;

/// <summary>
/// Metadata about a voice actor
/// </summary>
@Data
public class VoiceActorMetadata {
    /// <summary>
    /// Staff Id
    /// </summary>
    private Integer id;
    /// <summary>
    /// Staff Alias ID
    /// </summary>
    private Integer aid;
    /// <summary>
    /// Visual Novel ID
    /// </summary>
    private Integer vid;
    /// <summary>
    /// Notes
    /// </summary>
    private String note;
}

