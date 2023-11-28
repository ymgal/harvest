package com.ymgal.model.Release;

import com.ymgal.model.Common.ProducerCommon;
import lombok.Data;

/// <summary>
/// Producer Involved in the Release
/// </summary>
@Data
public class ProducerRelease extends ProducerCommon {
    /// <summary>
    /// Is a developer
    /// </summary>
    private Boolean developer;
    /// <summary>
    /// Is a publisher
    /// </summary>
    private Boolean publisher;
}

