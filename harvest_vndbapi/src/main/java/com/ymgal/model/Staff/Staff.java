package com.ymgal.model.Staff;

import lombok.Data;

import java.util.List;

/// <summary>
/// Staff Information
/// </summary>
@Data
public class Staff {
    /// <summary>
    /// Staff Id
    /// </summary>
    private Integer id;
    /// <summary>
    /// Staff Name
    /// </summary>
    private String name;
    /// <summary>
    /// Staff Original Name
    /// </summary>
    private String original;
    /// <summary>
    /// Staff Gender
    /// </summary>
    //private Gender gender;
    private String gender;

    /// <summary>
    /// Primary Language
    /// </summary>
    private String language;
    /// <summary>
    /// Related Staff links
    /// </summary>
    private StaffLinks links;
    /// <summary>
    /// Staff Description
    /// </summary>
    private String description;
    /// <summary>
    /// List of names and aliases
    /// </summary>
    //private List<StaffAliases> aliases;
    private List<Object[]> aliases;
    /// <summary>
    /// Main alias
    /// </summary>
    private Integer main_alias;
    /// <summary>
    /// Vns that the staff member has worked on
    /// </summary>
    private List<StaffVns> vns;
    /// <summary>
    /// List of Characters this staff has voiced
    /// </summary>
    private List<StaffVoiced> voiced;
}

