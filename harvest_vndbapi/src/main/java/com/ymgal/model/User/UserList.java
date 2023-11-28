package com.ymgal.model.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/// <summary>
/// User Lists
/// </summary>
@Data
public class UserList {
    /// <summary>
    /// User ID
    /// </summary>
    @JsonProperty("uid")
    private Integer UserId;
    /// <summary>
    /// Visual Novel ID
    /// </summary>
    @JsonProperty("vn")
    private Integer VisualNovelId;
    /// <summary>
    /// Added on date
    /// </summary>
    @JsonProperty("added")
    private LocalDateTime AddedOn;
    /// <summary>
    /// Last modified date
    /// </summary>
    @JsonProperty("lastmod")
    private LocalDateTime LastModified;
    /// <summary>
    /// Voted on date
    /// </summary>
    @JsonProperty("voted")
    private LocalDateTime VotedOn;
    /// <summary>
    /// Current vote (between 10 and 100)
    /// </summary>
    private Integer Vote;
    /// <summary>
    /// Notes
    /// </summary>
    private String Notes;
    /// <summary>
    /// Started playing date
    /// </summary>
    private Date Started;
    /// <summary>
    /// Finished playing date
    /// </summary>
    private Date Finished;
    /// <summary>
    /// Collection of User Labels
    /// </summary>
    private List<UserLabels> Labels;

}

