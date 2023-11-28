package com.ymgal.model.Errors;

/// <summary>
///		Represents the type of Throttling that occured
/// </summary>
public enum ThrottledType {
    /// <summary>
    ///		VndbSharp was unable to determine the Throttle Type
    /// </summary>
    Unknown,
    /// <summary>
    ///		The throttle is due to exceeding the commands / 10 minute limit
    /// </summary>
    Command,
    /// <summary>
    ///		The throttle is due to exceeding the sql time / minute limit
    /// </summary>
    Sql,
}
