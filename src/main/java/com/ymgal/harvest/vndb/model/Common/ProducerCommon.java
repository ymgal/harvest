package com.ymgal.harvest.vndb.model.Common;

import com.ymgal.harvest.model.RealNameLiberation;
import lombok.Data;

/// <summary>
/// Common Producer properties
/// </summary>
@Data
public class ProducerCommon implements RealNameLiberation {
    /// <summary>
    /// Producer ID
    /// </summary>
    private Integer id;
    /// <summary>
    /// Producer's Name
    /// </summary>
    private String name;
    /// <summary>
    /// Producer's Original/Official Name
    /// </summary>
    private String original;
    /// <summary>
    /// Type of the producer
    /// </summary>
    private String type; // Enum? Valid values - https://g.blicky.net/vndb.git/tree/util/sql/all.sql#n20 , real values???
}

