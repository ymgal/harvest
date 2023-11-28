package com.ymgal.model;

import lombok.Data;

/// <summary>
/// Class for defining the version of the API
/// </summary>
@Data
public class ApiVersionInfo {
    /// <summary>
    /// Date of the API Version
    /// </summary>
    public String ApiVersion;
    /// <summary>
    /// Completion status of VndbSharp's implementation of the Vndb API
    /// </summary>
    public VersionStatus ApiStatus;
}

