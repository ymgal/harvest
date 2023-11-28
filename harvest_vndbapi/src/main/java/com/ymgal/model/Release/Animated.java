package com.ymgal.model.Release;

/// <summary>
/// Animated Properties
/// </summary>
public enum Animated {
    /// <summary>
    /// Not animated
    /// </summary>
    Not(1),
    /// <summary>
    /// Simple animations
    /// </summary>
    Simple(2),
    /// <summary>
    /// Partial animations
    /// </summary>
    Partial(3),
    /// <summary>
    /// Fully animated
    /// </summary>
    Full(4);

    private final Integer animated;

    Animated(Integer animated) {
        this.animated = animated;
    }

    public Integer getAnimated() {
        return animated;
    }
}