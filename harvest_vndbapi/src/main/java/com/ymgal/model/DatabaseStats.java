package com.ymgal.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/// <summary>
/// Stats about the VNDB Database
/// </summary>
@Data
public class DatabaseStats {
    /// <summary>
    /// AMount of Users in the DB
    /// </summary>
    public Integer Users;
    /// <summary>
    /// Amount of Threads in the DB
    /// </summary>
    public Integer Threads;
    /// <summary>
    /// Amount of Tags in the DB
    /// </summary>
    public Integer Tags;
    /// <summary>
    /// AMount of Releases in the DB
    /// </summary>
    public Integer Releases;
    /// <summary>
    /// Amount of Producers in the DB
    /// </summary>
    public Integer Producers;
    /// <summary>
    /// Amount of Characters in the DB
    /// </summary>
    @JsonProperty("chars")
    public Integer Characters;
    /// <summary>
    /// Amount of Posts in the DB
    /// </summary>
    public Integer Posts;
    /// <summary>
    /// Amount of VNs in the DB
    /// </summary>
    @JsonProperty("vn")
    public Integer VisualNovels;
    /// <summary>
    /// Amount of Traits in the DB
    /// </summary>
    public Integer Traits;
}

