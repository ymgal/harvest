package com.ymgal.harvest.vndb.model.Producer;

import com.ymgal.harvest.vndb.model.Common.ProducerCommon;
import lombok.Data;

import java.util.List;

/// <summary>
/// Producer Information
/// </summary>
@Data
public class Producer extends ProducerCommon {
    /// <summary>
    /// Primary Language
    /// </summary>
    private String language;
    /// <summary>
    /// Producer Links
    /// </summary>
    private ProducerLinks links;
    /// <summary>
    /// List of alternative names
    /// </summary>
    private String aliases;
    /// <summary>
    /// Description/notes of the producer, can contain formatting codes
    /// </summary>
    private String description;
    /// <summary>
    /// List of related producers
    /// </summary>
    private List<Relationship> relations;
}

