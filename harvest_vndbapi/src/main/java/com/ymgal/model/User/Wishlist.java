package com.ymgal.model.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ymgal.model.Common.Priority;
import lombok.Data;

import java.time.LocalDateTime;

/// <summary>
/// User Wishlist
/// </summary>
@Data
public class Wishlist {
    /// <summary>
    /// Visual Novel ID
    /// </summary>
    @JsonProperty("vn")
    private Integer VisualNovelId;
    /// <summary>
    /// User ID
    /// </summary>
    @JsonProperty("uid")
    private Integer UserId;
    /// <summary>
    /// Wishlist Priority
    /// </summary>
    private Priority Priority;
    /// <summary>
    /// Added on Date
    /// </summary>
    @JsonProperty("added")
    private LocalDateTime AddedOn;
}

