package com.ymgal.harvest.model.archive;

import com.ymgal.harvest.model.ExtensionName;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;
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
    @Min(0)
    @Max(2)
    private Integer gender;
}
