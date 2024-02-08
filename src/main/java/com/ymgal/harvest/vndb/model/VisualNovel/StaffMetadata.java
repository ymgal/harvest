package com.ymgal.harvest.vndb.model.VisualNovel;

import com.ymgal.harvest.model.RealNameLiberation;
import lombok.Data;

/// <summary>
/// Staff Metadata
/// </summary>
@Data
public class StaffMetadata implements RealNameLiberation {
    /// <summary>
    /// Staff ID
    /// </summary>
    private Integer sid;
    /// <summary>
    /// Alias ID
    /// </summary>
    private Integer aid;
    /// <summary>
    /// English Name
    /// </summary>
    private String name;

    /// <summary>
    /// Japanese Name
    /// </summary>
    private String original;
    /// <summary>
    ///		The role they served as staff
    /// </summary>
    private String role; // TODO: Convert to enum
    /// <summary>
    ///		Contains more info on their role as staff
    /// </summary>
    private String note;

    private StaffMetadata() {
    }
}

