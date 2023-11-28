package com.ymgal.model.Common;

import lombok.Data;

/// <summary>
/// Rating of the image (sexual and violence levels)
/// </summary>
@Data
public class ImageRating {
    /// <summary>
    /// Amount of votes for the rating
    /// </summary>
    private Integer votecount;
    /// <summary>
    /// Average of sexual rating, score between 0 and 2
    /// Levels are 0(Safe), 1(Suggestive), and 3(Explicit)
    /// </summary>
    private Double sexual_avg;
    /// <summary>
    /// Average of violence rating, score between 0 and 2
    /// Levels are 0(Tame), 1(Violent), and 3(Brutal)
    /// </summary>
    private Double violence_avg;
}
