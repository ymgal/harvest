package com.ymgal.model.Errors;

/// <summary>
///		A JSON value is of the wrong type or in the wrong format.
/// </summary>
public class BadArgumentError extends Error {
    /// <summary>
    ///		The field that is incorrect
    /// </summary>
    public String Field;
}

