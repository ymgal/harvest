package com.ymgal.harvest.model;


import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Website {

    /**
     * 网站标题、描述
     */
    @NotEmpty(message = "网站标题不能为空")
    private String title;

    /**
     * 网站链接
     */
    @NotEmpty(message = "网站链接不能为空")
    private String link;
}