package com.ymgal.model.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ymgal.model.Common.Status;
import lombok.Data;

import java.time.LocalDateTime;

/// <summary>
/// List of VNs on a user's list
/// </summary>
@Data
public class VisualNovelList {
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
    /// Visual Novel Status
    /// </summary>
    private Status Status;
    /// <summary>
    /// Added on Date
    /// </summary>
    @JsonProperty("added")
    private LocalDateTime AddedOn;
    /// <summary>
    /// Notes
    /// </summary>
    private String Notes;
}

