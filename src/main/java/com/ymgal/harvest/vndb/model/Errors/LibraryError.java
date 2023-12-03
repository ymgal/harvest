package com.ymgal.harvest.vndb.model.Errors;

/// <summary>
/// 	An error occured in the VndbSharp Library
/// </summary>
public class LibraryError extends Error {
    protected LibraryError(String message) {
        this.Type = ErrorType.Library;
        this.Message = message;
    }
}

