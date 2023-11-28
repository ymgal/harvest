package com.ymgal.model.Errors;

/// <summary>
///		A JSON object argument is missing a required member.
/// </summary>
public class MissingError extends Error {
    /// <summary>
    ///		The field that is incorrect
    /// </summary>
    public String Field;
}
