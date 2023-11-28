package com.ymgal.harvest.model.archive;

import com.ymgal.harvest.model.ExtensionName;
import com.ymgal.harvest.model.Website;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
public class GameArchive {

    /**
     * vndb的ID
     */
    private Integer vndbId;

    /**
     * 开发商ID
     * vndb的pid
     */
    @NotNull(message = "开发商ID不能为null")
    private Integer developer;

    /**
     * 是否有汉化
     */
    @NotNull(message = "是否有汉化不能为null")
    private Boolean haveChinese;

    /**
     * 游戏类型描述
     */
    @NotNull(message = "游戏类型描述不能为null")
    private String typeDesc;

    @NotEmpty(message = "名称不能为空")
    private String name;

    /**
     * 拓展名称
     */
    @Valid
    @NotNull(message = "拓展名列表不能为null")
    private List<ExtensionName> extensionName;

    /**
     * 简介，富文本
     */
    @NotNull(message = "简介不能为null")
    private String introduction;

    private String mainImg;

    private LocalDate releaseDate;

    @NotNull(message = "是否是受限制的不能为null")
    private Boolean restricted;

    /**
     * 相关网站
     */
    @Valid
    @NotNull(message = "网站不能为null")
    private List<Website> website;

    @Valid
    @NotNull(message = "游戏档案中的characters 不能为null")
    private List<Characters> characters;

    @Valid
    @NotNull(message = "游戏档案中的releases 不能为null")
    private List<Release> releases;

    @Valid
    @NotNull(message = "游戏档案中的staff 不能为null")
    private List<Staff> staff;


    @Data
    @AllArgsConstructor
    public class Characters {

        /**
         * 角色ID， vndbCid
         */
        @NotNull(message = "vndb角色 cid不能为null")
        private Integer cid;

        /**
         * 声优的用户ID， vndb的sid vndbSid
         */
        private Integer cvId;

        /**
         * 此角色在此游戏中是什么位置
         * 1=主角
         * 2=配角
         */
        @NotNull(message = "关联的角色定位不能为null")
        @Size(min = 0, max = 1, message = "position 填写错误，只能是0/1间的值")
        private Integer position;
    }

    @Data
    @AllArgsConstructor
    public class Release {

        /**
         * 发布的名称
         */
        @NotEmpty(message = "发布名称不能为空")
        private String releaseName;

        /**
         * 发布链接
         */
        private String relatedLink;

        /**
         * 发布平台
         */
        @NotEmpty(message = "发布平台不能为空")
        private String platform;

        /**
         * 发布时间
         */
        private LocalDate releaseDate;

        /**
         * 发布语言
         */
        @NotEmpty(message = "发布语言不能为空")
        private String country;

        /**
         * 限制级别
         */
        @NotEmpty(message = "限制级别不能为空")
        private String restrictionLevel;
    }

    @Data
    @AllArgsConstructor
    public class Staff {

        /**
         * 人类档案ID _ 对应的vndbSID
         */
        @NotNull(message = "STAFF vnsid不能为空")
        private Integer sid;

        /**
         * 员工参与时使用的名字
         */
        @NotEmpty(message = "STAFF员工名不能为空")
        private String empName;

        /**
         * 员工备注
         */
        private String desc;

        /**
         * 岗位职称
         */
        @NotEmpty(message = "STAFF员工岗位不能为空")
        private String jobName;
    }
}
