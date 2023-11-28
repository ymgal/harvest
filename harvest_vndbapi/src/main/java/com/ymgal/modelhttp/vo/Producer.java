package com.ymgal.modelhttp.vo;

import lombok.Data;

import java.util.List;

/**
 * @Auther: lyl
 * @Date: 2023/10/16 10:54
 * @Description:
 */
@Data
public class Producer {
    private String id;

    private String name;
    private String original;
    private List<String> aliases;
    private String lang;
    private String type;
    private String description;
}
