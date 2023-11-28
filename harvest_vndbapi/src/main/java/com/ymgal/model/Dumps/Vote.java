package com.ymgal.model.Dumps;

import lombok.Data;

import java.util.Date;

/// <summary>
/// Vote Object from the Dump
/// </summary>
@Data
public class Vote {
    /// <summary>
    /// Version of the Dump
    /// </summary>
    private VoteDumpVersion Version;
    /// <summary>
    /// Visual Novel ID
    /// </summary>
    private Integer VisualNovelId;
    /// <summary>
    /// User ID
    /// </summary>
    private Integer UserID;
    /// <summary>
    /// Vote Value (Between 10 and 100)
    /// </summary>
    private Byte Value;
    /// <summary>
    /// Date the vote was added on
    /// </summary>
    private Date AddedOn;

    protected Vote(VoteDumpVersion version, Integer visualNovelId, Integer userId, Byte value, Date addedOn) {
        this.Version = version;
        this.VisualNovelId = visualNovelId;
        this.UserID = userId;
        this.Value = value;
        this.AddedOn = addedOn;
    }
}

