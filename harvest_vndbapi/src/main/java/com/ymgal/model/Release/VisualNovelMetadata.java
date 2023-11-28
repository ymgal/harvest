package com.ymgal.model.Release;

import lombok.Data;

/// <summary>
/// Visual Novel Info related to Release
/// </summary>
@Data
public class VisualNovelMetadata {
    /// <summary>
    /// Visual Novel ID
    /// </summary>
    private Integer id;
    /// <summary>
    /// Visual Novel Name
    /// </summary>
    private String title;
    /// <summary>
    /// Visaul Novel Original Name
    /// </summary>
    private String original;

    private String rtype;
}
