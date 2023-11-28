package com.ymgal;

/// <summary>
/// Class for dealing with an unexpected response from the API
/// </summary>
public class UnexpectedResponseException extends Exception {
    /// <summary>
    /// Command that was sent
    /// </summary>
    public String Command;
    /// <summary>
    /// Response received from the API
    /// </summary>
    public String Response;

    /// <summary>
    /// Gets/Sets the command/response objects from an unexpected response
    /// </summary>
    /// <param name="command"></param>
    /// <param name="response"></param>
    public UnexpectedResponseException(String command, String response) {
        this.Command = command;
        this.Response = response;
    }
}
