package com.ymgal.harvest;

import java.net.InetSocketAddress;
import java.util.Collections;

public class VndbHarvest extends Harvest {

    private static final String PREFIX = "https://vndb.org/v";

    public VndbHarvest(String gameUrl) {
        super(gameUrl);
    }

    @Override
    protected void validateUrl(String gameUrl) {
        if (gameUrl.startsWith(PREFIX)) return;
        throw new IllegalArgumentException("VNDB address parsing error: " + gameUrl);
    }

    @Override
    protected HarvestResult exec(String gameUrl, InetSocketAddress proxy) {
        //TODO...

        return HarvestResult.ok(null, null, Collections.emptyList(), Collections.emptyList());
    }
}
