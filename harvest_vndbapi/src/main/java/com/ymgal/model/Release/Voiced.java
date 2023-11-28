package com.ymgal.model.Release;

/// <summary>
/// Voiced Status
/// </summary>
public enum Voiced {
    /// <summary>
    /// Not voiced at all
    /// </summary>
    Not(1),
    /// <summary>
    /// Ero/H-Scenes only
    /// </summary>
    Ero(2),
    /// <summary>
    /// Partially voiced
    /// </summary>
    Partial(3),
    /// <summary>
    /// Fully voiced
    /// </summary>
    Full(4);

    private final Integer voiced;

    Voiced(Integer voiced) {
        this.voiced = voiced;
    }

    public Integer getVoiced() {
        return voiced;
    }
}
