package com.ymgal.harvest.model;

public interface RealNameLiberation {

    String getName();

    String getOriginal();

    default String realName() {
        String realName = getOriginal();
        return realName == null || realName.isEmpty() ? getName() : realName;
    }
}
