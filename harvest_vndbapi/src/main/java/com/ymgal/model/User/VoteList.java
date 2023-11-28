package com.ymgal.model.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

/// <summary>
/// User VoteList
/// </summary>
@Data
public class VoteList {
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
    /// Current Vote (between 10 and 100)
    /// </summary>
    private Integer Vote;
    /// <summary>
    /// Added on Date
    /// </summary>
    @JsonProperty("added")
    private LocalDateTime AddedOn;
}

