package com.ymgal.harvest;

public class HarvestTests {

    public static void main(String[] args) {
        //clannad
        Harvest harvest = new VndbHarvest("https://vndb.org/v4");
        harvest.get().thenApply(r -> {
            ValidateUtil.validate(r);
            return r;
        }).thenAccept(System.out::println).join();
    }
}
