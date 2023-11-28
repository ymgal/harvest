package com.ymgal.model.VisualNovel;

import lombok.Data;

import java.util.Date;

/// <summary>
/// Anime Metadata
/// </summary>
@Data
public class AnimeMetadata {
    /// <summary>
    /// AniDb ID
    /// </summary>
    private Integer id;
    /// <summary>
    /// AnimeNewsNetwork ID
    /// </summary>
    private Integer ann_id;
    /// <summary>
    /// AnimeNfo ID
    /// </summary>
    private String nfo_id;
    /// <summary>
    /// English Title
    /// </summary>
    private String title_romaji;
    /// <summary>
    /// Japanese Title
    /// </summary>
    private String title_kanji;
    /// <summary>
    /// Year anime aired
    /// </summary>
    private Date year;
    /// <summary>
    /// Anime Type
    /// </summary>
    private String type; // ??
}

