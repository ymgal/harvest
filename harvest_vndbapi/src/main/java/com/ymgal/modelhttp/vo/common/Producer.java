package com.ymgal.modelhttp.vo.common;

import lombok.Data;

import java.util.List;

/**
 * @Auther: lyl
 * @Date: 2023/10/16 10:39
 * @Description:
 */
@Data
public class Producer {

    private String id;
    private String name;
    private String description;
    private String type;
    private List<String> aliases;
    private String original;
    private String lang;

}
