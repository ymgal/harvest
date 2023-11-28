package com.ymgal.model.Release;

import lombok.Data;

/// <summary>
/// Release media type (DVD, internet,...)
/// </summary>
@Data
public class Media {
    /// <summary>
    /// Medium Type
    /// </summary>
    private String medium;
    /// <summary>
    /// Quantity of the medium
    /// </summary>
    private Integer qty;
}

