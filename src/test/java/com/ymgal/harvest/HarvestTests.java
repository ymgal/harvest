package com.ymgal.harvest;

import java.net.InetSocketAddress;

public class HarvestTests {

    public static void main(String[] args) throws Exception {
        //clannad
        Harvest harvest = new VndbHarvest("https://vndb.org/v4");
//        harvest.get().thenApply(r -> {
//            ValidateUtil.validate(r);
//            return r;
//        }).thenAccept(System.out::println).join();

        HarvestResult harvestResult = harvest.exec("https://vndb.org/v117", new InetSocketAddress(80));
    }
}
