package com.ymgal.model.Dumps;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/// <summary>
///		Represents a Tag Object from the Tags Dump
/// </summary>
@Data
public class Tag {
    /// <summary>
    ///		The Id of the tag
    /// </summary>
    private Integer Id;
    /// <summary>
    ///		The name of the tag
    /// </summary>
    private String Name;
    /// <summary>
    ///		The description of the tag, which can include formatting codes described in http://vndb.org/d9.3
    /// </summary>
    private String Description;
    /// <summary>
    ///		Whether this is a meta tag or not. This field only exists for backwards compatibility and is currently the inverse of "searchable".
    /// </summary>
    @JsonProperty("meta")
    private Boolean IsMeta;
    /// <summary>
    /// Whether it's possible to filter VNs by this tag.
    /// </summary>
    private Boolean Searchable;
    /// <summary>
    /// Whether this tag can be applied to VN entries.
    /// </summary>
    private Boolean Applicable;
    /// <summary>
    ///		The number of Visual Novels with this tag
    /// </summary>
    @JsonProperty("vns")
    private Integer VisualNovels;
    /// <summary>
    ///		The category / Classification of the tag
    /// </summary>
    @JsonProperty("cat")
    private TagCategory TagCategory;
    /// <summary>
    ///		List of alternative names
    /// </summary>
    private List<String> Aliases;
    /// <summary>
    ///		List of parent Tags (Empty if Root Tag)
    /// </summary>
    private List<Integer> Parents;

}

