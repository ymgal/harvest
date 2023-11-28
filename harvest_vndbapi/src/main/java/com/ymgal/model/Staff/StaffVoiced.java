package com.ymgal.model.Staff;

import lombok.Data;

/// <summary>
/// Characters the staff has voiced
/// </summary>
@Data
public class StaffVoiced {
    /// <summary>
    /// Staff Id
    /// </summary>
    private Integer id;
    /// <summary>
    /// Alias Id
    /// </summary>
    private Integer aid;
    /// <summary>
    /// Character Id
    /// </summary>
    private Integer cid;
    /// <summary>
    /// Notes
    /// </summary>
    private String note;
}

