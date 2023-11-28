package com.ymgal.modelhttp.enums;

public enum FilterName {
    ID("id"),
    SEARCH("search"),
    LANG("lang"),
    OLANG("olang"),
    TYPE("type"),
    PLATFORM("platform"),
    LENGTH("length"),
    RELEASED("released"),
    RATING("rating"),
    VOTECOUNT("votecount"),
    HAS_DESCRIPTION("has_description"),
    HAS_ANIME("has_anime"),
    HAS_SCREENSHOT("has_screenshot"),
    HAS_REVIEW("has_review"),
    DEVSTATUS("devstatus"),
    TAG("tag"),
    DTAG("dtag"),
    ANIME_ID("anime_id"),
    LABEL("label"),
    RELEASE("release"),
    CHARACTER("character"),
    STAFF("staff"),
    DEVELOPER("developer");
    private final String filterName;

    FilterName(String field) {
        this.filterName = field;
    }

    public static FilterName fromString(String field) {
        for (FilterName op : FilterName.values()) {
            if (op.filterName.equals(field)) {
                return op;
            }
        }
        throw new IllegalArgumentException("Invalid field: " + field);
    }

    public String getFilterName() {
        return this.filterName;
    }
}
