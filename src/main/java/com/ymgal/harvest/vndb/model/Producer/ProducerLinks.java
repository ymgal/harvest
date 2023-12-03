package com.ymgal.harvest.vndb.model.Producer;

import com.ymgal.harvest.vndb.model.Common.CommonLinks;
import lombok.Data;

/// <summary>
/// Links related to a Producer
/// </summary>
@Data
public class ProducerLinks extends CommonLinks {
    /// <summary>
    /// Producer's homepage
    /// </summary>
    private String homepage;
}

