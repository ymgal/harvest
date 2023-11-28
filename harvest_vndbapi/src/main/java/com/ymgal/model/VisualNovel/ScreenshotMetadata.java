package com.ymgal.model.VisualNovel;

import com.ymgal.model.Common.ImageRating;
import lombok.Data;

/// <summary>
/// Screenshot Metadata
/// </summary>
@Data
public class ScreenshotMetadata {
    /// <summary>
    /// Image URL
    /// </summary>
    private String image;
    /// <summary>
    /// Release ID
    /// </summary>
    private Integer rid;
    /// <summary>
    /// Is Image NSFW
    /// </summary>
    private Boolean nsfw;
    /// <summary>
    /// Violence/Sexual rating of Image
    /// </summary>
    private ImageRating flagging;
    /// <summary>
    /// Image height in pixels
    /// </summary>
    private Integer height;
    /// <summary>
    /// Image width in pixels
    /// </summary>
    private Integer width;
}
