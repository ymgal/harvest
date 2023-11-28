package com.ymgal.model.Dumps;

/// <summary>
///		The version of the Vote Dump retrieved
/// </summary>
public enum VoteDumpVersion {
    /// <summary>
    ///		Represents the most basic vote dump
    /// </summary>
    One(1),
    /// <summary>
    ///		Represents the v2 of the vote dump, which adds Dates
    /// </summary>
    Two(2),
    ;

    private final Integer version;

    VoteDumpVersion(Integer version) {
        this.version = version;
    }

    public Integer getVersion() {
        return version;
    }
}
