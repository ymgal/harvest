package com.ymgal.model.Errors;

import com.fasterxml.jackson.annotation.JsonProperty;

/// <summary>
///		Unknown filter field or invalid combination of field / operator / argument type
/// </summary>
public class InvalidFilterError extends Error {
    /// <summary>
    ///		The field given
    /// </summary>
    public String Field;
    /// <summary>
    ///		The operator given
    /// </summary>
    @JsonProperty("op")
    public String Operator;
    /// <summary>
    ///		The value given
    /// </summary>
    public Object Value;
}
