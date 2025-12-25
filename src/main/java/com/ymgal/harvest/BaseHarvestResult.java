package com.ymgal.harvest;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseHarvestResult {

    private String gameUrl;

    private LocalDateTime taskStartTime;

    private Exception error;
}
