package com.ymgal.model.Release;

import lombok.Data;

import java.util.List;

/// <summary>
/// Release Information
/// </summary>
@Data
public class Release {
    /// <summary>
    /// Release ID
    /// </summary>
    private Integer id;
    /// <summary>
    /// Release Name
    /// </summary>
    private String title;
    /// <summary>
    /// Release Original Name
    /// </summary>
    private String original;
    /// <summary>
    /// Date of Release
    /// </summary>
    private String released;
    /// <summary>
    /// Release Type
    /// </summary>
    private String type;
    /// <summary>
    /// Is the Release a patch
    /// </summary>
    private Boolean patch;
    /// <summary>
    /// Is the Release freeware
    /// </summary>
    private Boolean freeware;
    /// <summary>
    /// Is the Release a doujin
    /// </summary>
    private Boolean doujin;
    /// <summary>
    /// List of languages
    /// </summary>
    private List<String> languages;
    /// <summary>
    /// Release Website
    /// </summary>
    private String website;
    /// <summary>
    /// Release Notes
    /// </summary>
    private String notes; // Possibly rename to description
    /// <summary>
    /// Minimum age
    /// </summary>
    private Integer minage;
    /// <summary>
    ///		JAN/UPC/EAN code.
    /// </summary>
    private String gtin;
    /// <summary>
    /// Catalog Number
    /// </summary>
    private String catalog;
    /// <summary>
    /// Resolution of Release
    /// </summary>
    private String resolution;
    /// <summary>
    /// Voiced Type
    /// </summary>
    // private Voice voiced;
    private Integer voiced;

    private Boolean official;
    /// <summary>
    ///		The array has two integer members, the first one indicating the story animations, the second the ero scene animations. Both members can be null if unknown or not applicable.
    /// </summary>
    private List<Animated> animation;
    /// <summary>
    /// Release Platforms
    /// </summary>
    private List<String> platforms;
    /// <summary>
    /// Release Media
    /// </summary>
    private List<Media> media;
    /// <summary>
    /// Related Visual Novels
    /// </summary>
    private List<VisualNovelMetadata> vn;
    /// <summary>
    /// Related Producers
    /// </summary>
    private List<ProducerRelease> producers;
}

