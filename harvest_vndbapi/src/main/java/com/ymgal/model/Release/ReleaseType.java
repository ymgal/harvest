package com.ymgal.model.Release;

/// <summary>
/// Type of Release
/// </summary>
public enum ReleaseType {
    /// <summary>
    /// Trial Version
    /// </summary>
    Trial,
    /// <summary>
    /// Partial Version, normally used by patches
    /// </summary>
    Partial,
    /// <summary>
    /// Completed Release
    /// </summary>
    Complete,
}
