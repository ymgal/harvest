package com.ymgal.model.VisualNovel;

import lombok.Data;

/// <summary>
/// Visual Novel Relations
/// </summary>
@Data
public class VisualNovelRelation {
    /// <summary>
    /// Visual Novel Id
    /// </summary>
    private Integer id;
    /// <summary>
    /// Relation Type
    /// </summary>
    private String relation;
    /// <summary>
    /// Title
    /// </summary>
    private String title;
    /// <summary>
    /// Original Title
    /// </summary>
    private String original;
    /// <summary>
    /// Is Official relation
    /// </summary>
    private Boolean official;

}
