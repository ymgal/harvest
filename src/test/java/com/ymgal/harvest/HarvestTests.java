package com.ymgal.harvest;


import com.ymgal.VndbHarvestMethod;
import com.ymgal.dto.HarvestResult;


public class HarvestTests {

    public static void main(String[] args) throws Exception {
        //clannad
        Harvest harvest = new VndbHarvest("https://vndb.org/v4");
//        harvest.get().thenApply(r -> {
//            ValidateUtil.validate(r);
//            return r;
//        }).thenAccept(System.out::println).join();

        HarvestResult vndbHarvest = VndbHarvestMethod.getVndbHarvest(17);
    }
}
