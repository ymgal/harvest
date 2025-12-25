package com.ymgal.harvest;

import java.time.LocalDateTime;

/**
 * 包含任务通用元信息的接口
 * 用于标识爬取任务的 URL、开始时间以及可能的异常信息
 */
public interface HarvestMetadata {

    String getGameUrl();

    void setGameUrl(String gameUrl);

    LocalDateTime getTaskStartTime();

    void setTaskStartTime(LocalDateTime taskStartTime);

    Exception getError();

    void setError(Exception error);
}