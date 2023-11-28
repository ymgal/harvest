package com.ymgal.harvest.model.archive;

import com.ymgal.harvest.model.ExtensionName;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
public class CharacterArchive {

    private Integer vndbCid;

    @NotEmpty(message = "名称必传")
    private String name;

    @NotNull(message = "拓展名必传")
    @Valid
    private List<ExtensionName> extensionNames;

    @NotNull(message = "简介 必传")
    private String introduction;

    private LocalDate birthday;

    private String mainImg;

    //性别： 0=未知 1=男 2=女
    @NotNull(message = "性别不能为null")
    @Size(min = 0, max = 1, message = "gender 填写错误，只能是0/1间的值")
    private Integer gender;
}
