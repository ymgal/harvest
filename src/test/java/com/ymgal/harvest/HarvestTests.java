package com.ymgal.harvest;


import com.ymgal.harvest.vndb.helper.JsonHelper;

import java.time.Duration;
import java.time.LocalDateTime;

public class HarvestTests {

    public static void main(String[] args) throws Exception {
        //https://vndb.org/v33272
        Harvest harvest = new VndbHarvest("https://vndb.org/v49163");

        harvest.get().thenApply(r -> {
            Duration between = Duration.between(r.getTaskStartTime(), LocalDateTime.now());
            System.out.println("获取成功 >>>>>>>>> 总共花费时间为: "+ between.toMillis());

            if (r.getError() != null){
                r.getError().printStackTrace();
            }

            ValidateUtil.validate(r);
            return r;
        }).thenApply(JsonHelper::serialize).thenAccept(System.out::println).join();
    }
}