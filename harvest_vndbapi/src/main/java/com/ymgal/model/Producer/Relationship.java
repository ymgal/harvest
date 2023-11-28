package com.ymgal.model.Producer;

import lombok.Data;

/// <summary>
/// Producer Relation info
/// </summary>
@Data
public class Relationship {
    /// <summary>
    /// Producer ID
    /// </summary>
    private Integer id;
    /// <summary>
    /// Relation to the current producer
    /// </summary>
    private String relation; // TODO: Enum?
    /// <summary>
    /// Producer name
    /// </summary>
    private String name;
    /// <summary>
    /// Original name
    /// </summary>
    private String original;
}
