package com.ymgal.model.Staff;

import com.ymgal.model.Common.CommonLinks;
import lombok.Data;

/// <summary>
/// List of Staff Links
/// </summary>
@Data
public class StaffLinks extends CommonLinks {
    /// <summary>
    /// Official Homepage
    /// </summary>
    private String homepage;
    /// <summary>
    /// Twitter page
    /// </summary>
    private String twitter;
    /// <summary>
    /// AniDb page
    /// </summary>
    private String anidb;
    /// <summary>
    /// Pixiv page
    /// </summary>
    private Integer pixiv;
}

