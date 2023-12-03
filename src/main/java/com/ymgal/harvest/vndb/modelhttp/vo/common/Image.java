package com.ymgal.harvest.vndb.modelhttp.vo.common;

import lombok.Data;

@Data
public class Image {
    private String id;
    private String url;
    private Integer[] dims;
    private Integer sexual;
    private Integer violence;
    private Integer votecount;
}
