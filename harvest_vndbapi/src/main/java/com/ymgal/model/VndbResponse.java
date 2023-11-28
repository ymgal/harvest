package com.ymgal.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.List;

@Data
public class VndbResponse<T> {
    /// <summary>
    /// If there are more items available
    /// </summary>
    private Boolean more;

    private Integer num;
    /// <summary>
    /// Amount of items received
    /// </summary>
    /// <summary>
    /// Collection of entries. This is what holds the main data
    /// </summary>
    @JsonAlias({"items", "results"})
    private List<T> items;

    // Disable publicly constructing the Response Object
    public VndbResponse() {
    }
}

