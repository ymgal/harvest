package com.ymgal.model;

import com.ymgal.Interfaces.IRequestOptions;

/// <summary>
/// Default RequestOptions For use when sending commands to the API
/// </summary>
public class RequestOptions implements IRequestOptions {
    /// <summary>
    /// What Page to get
    /// </summary>
    public Integer Page;
    /// <summary>
    /// How many entries to get. Most commands are limited at 25, except for votelist/vnlist/wishlist/ulist, which returns at most 100 results.
    /// </summary>
    public Integer Count;
    /// <summary>
    /// The field to order the results by
    /// </summary>
    public String Sort;
    /// <summary>
    /// Reverses the order of the entries
    /// </summary>
    public Boolean Reverse;
}

