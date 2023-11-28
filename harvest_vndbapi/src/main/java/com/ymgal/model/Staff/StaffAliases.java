package com.ymgal.model.Staff;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/// <summary>
/// Staff Aliases
/// </summary>
@Data
public class StaffAliases {
    /// <summary>
    /// Staff Id
    /// </summary>
    private Integer Id;
    /// <summary>
    /// Staff Name
    /// </summary>
    private String Name;
    /// <summary>
    /// Staff Original Name
    /// </summary>
    @JsonProperty("original")
    private String OriginalName;
}

