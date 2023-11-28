package com.ymgal.modelhttp;

import lombok.Data;

import java.util.List;

/**
 * @Auther: lyl
 * @Date: 2023/10/13 09:30
 * @Description:
 */
@Data
public class RequestBody {

    List<String> filters;

    String fields;
}
