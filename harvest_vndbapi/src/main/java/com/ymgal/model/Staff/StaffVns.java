package com.ymgal.model.Staff;

import lombok.Data;

/// <summary>
/// Visual Novels that the Staff worked on
/// </summary>
@Data
public class StaffVns {
    /// <summary>
    /// Staff Id
    /// </summary>
    private Integer id;
    /// <summary>
    /// Alias Id
    /// </summary>
    private Integer aid;
    /// <summary>
    ///		The role they served as staff
    /// </summary>
    private String role; // TODO: Convert to enum
    /// <summary>
    ///		Contains more info on their role as staff
    /// </summary>
    private String note;
}

