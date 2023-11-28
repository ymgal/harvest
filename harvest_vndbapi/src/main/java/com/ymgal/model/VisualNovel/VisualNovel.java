package com.ymgal.model.VisualNovel;

import com.ymgal.model.Common.ImageRating;
import lombok.Data;

import java.util.List;

/// <summary>
/// Visual Novel Info
/// </summary>
@Data
public class VisualNovel {
    /// <summary>
    /// Visual Novel ID
    /// </summary>
    private Integer id;
    /// <summary>
    /// Vn Name
    /// </summary>
    private String title;
    /// <summary>
    /// Vn Original Name
    /// </summary>
    private String original;
    /// <summary>
    /// Release Date
    /// </summary>
    private String released; // Release or Released?
    /// <summary>
    /// List of available languages
    /// </summary>
    private List<String> languages;
    /// <summary>
    /// languages of the first release
    /// </summary>
    private List<String> orig_lang;
    /// <summary>
    /// List of platforms released on
    /// </summary>
    private List<String> platforms;
    /// <summary>
    /// List of aliases for the game
    /// </summary>
    private String aliases;
    /// <summary>
    /// Estimated length of the game
    /// </summary>
    private VisualNovelLength Length;
    /// <summary>
    /// Description of the game. Can include formatting codes
    /// </summary>
    private String description;
    /// <summary>
    /// Links related to this game
    /// </summary>
    private VisualNovelLinks links;
    /// <summary>
    /// Cover Image URL
    /// </summary>
    private String image;
    /// <summary>
    /// Is Image NSFW
    /// </summary>
    private Boolean image_nsfw;
    /// <summary>
    /// Violence/Sexual rating of the cover image
    /// </summary>
    private ImageRating image_flagging;
    /// <summary>
    /// List of related Anime
    /// </summary>
    private List<AnimeMetadata> anime;
    /// <summary>
    /// List of related games
    /// </summary>
    private List<VisualNovelRelation> relations;
    /// <summary>
    /// List of associated Tags
    /// </summary>
    //private List<TagMetadata> tags;
    private List<Integer[]> tags;
    /// <summary>
    /// Popularity of the game (between 0 and 100)
    /// </summary>
    private Double popularity;
    /// <summary>
    /// Bayesian rating of the game (between 1 and 10)
    /// </summary>
    private Double rating;
    /// <summary>
    /// List of associated Screenshots
    /// </summary>
    private List<ScreenshotMetadata> screens;
    /// <summary>
    /// List of associated Staff
    /// </summary>
    private List<StaffMetadata> staff;

    private VisualNovel() {
    }
}

