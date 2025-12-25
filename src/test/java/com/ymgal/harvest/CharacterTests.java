package com.ymgal.harvest;

import com.ymgal.harvest.vndb.helper.JsonHelper;

import java.time.Duration;
import java.time.LocalDateTime;

public class CharacterTests {

    public static void main(String[] args) {

        Harvest<VndbClassifiedHarvest.Character> harvest = new VndbClassifiedHarvest().new CharacterHarvest("https://vndb.org/c100001");

        harvest.get().thenApply(r -> {
            Duration between = Duration.between(r.getTaskStartTime(), LocalDateTime.now());
            System.out.println("获取成功 >>>>>>>>> 总共花费时间为: " + between.toMillis());

            if (r.getError() != null) {
                r.getError().printStackTrace();
            }

            ValidateUtil.validate(r);
            return r;
        }).thenApply(JsonHelper::serialize).thenAccept(System.out::println).join();
    }
}
