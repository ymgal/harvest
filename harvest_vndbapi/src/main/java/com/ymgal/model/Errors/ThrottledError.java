package com.ymgal.model.Errors;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

/// <summary>
///		The Throttled Error. Occurs when the Vndb API is throttling you
/// </summary>
public class ThrottledError extends Error {
    /// <summary>
    ///		The type of Throttling that occured
    /// </summary>
    @JsonProperty("type")
    public ThrottledType ThrottledType;
    /// <summary>
    ///		<para>How long until you can start sending commands again</para>
    ///		<para>To check, do a comparsion of if (<see cref="DateTime.Now"/> > <see cref="MinimumWait"/>)</para>
    /// </summary>
    @JsonProperty("minwait")
    public OffsetDateTime MinimumWait;
    /// <summary>
    ///		<para>The recommended amount of time to wait before sending commands again</para>
    ///		<para>To check, do a comparsion of 'if (<see cref="DateTime.Now"/> > <see cref="FullWait"/>)'</para>
    /// </summary>
    public OffsetDateTime FullWait;
}

