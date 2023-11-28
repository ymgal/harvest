package com.ymgal.model.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/// <summary>
/// User Labels
/// </summary>
@Data
public class UserLabels {
    /// <summary>
    /// User ID
    /// </summary>
    @JsonProperty("uid")
    private Integer UserId;
    /// <summary>
    /// Label ID
    /// </summary>
    private Integer Id;
    /// <summary>
    /// Label Name
    /// </summary>
    private String Label;
    /// <summary>
    /// Is Label private
    /// </summary>
    @JsonProperty("private")
    private Boolean IsPrivate;
}

