package com.ymgal.harvest.vndb.model.Staff;

import com.ymgal.harvest.vndb.model.Common.CommonLinks;
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

