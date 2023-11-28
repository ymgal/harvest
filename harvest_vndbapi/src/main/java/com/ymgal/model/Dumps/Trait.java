package com.ymgal.model.Dumps;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/// <summary>
/// Represents a Trait Object from the Traits Dump
/// </summary>
@Data
public class Trait {
    /// <summary>
    ///		The Id of the trait
    /// </summary>
    private Integer Id;
    /// <summary>
    ///		The name of the trait
    /// </summary>
    private String Name;
    /// <summary>
    ///		The description of the trait, which can include formatting codes described in http://vndb.org/d9.3
    /// </summary>
    private String Description;
    /// <summary>
    ///		The number of characters with this trait
    /// </summary>
    @JsonProperty("chars")
    private Integer Characters;
    /// <summary>
    ///		List of alternative names
    /// </summary>
    private List<String> Aliases;
    /// <summary>
    ///		List of parent traits (Empty if root)
    /// </summary>
    private List<Integer> Parents;
    /// <summary>
    ///		Whether this is a meta trait or not.
    /// </summary>
    @JsonProperty("meta")
    private Boolean IsMeta;
    /// <summary>
    /// Whether it's possible to filter characters by this trait.
    /// </summary>
    private Boolean IsSearchable;
    /// <summary>
    ///  	Whether this trait can be applied to character entries.
    /// </summary>
    private Boolean IsApplicable;
}
