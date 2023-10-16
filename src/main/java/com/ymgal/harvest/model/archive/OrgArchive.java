package com.ymgal.harvest.model.archive;

import com.ymgal.harvest.model.ExtensionName;
import com.ymgal.harvest.model.Website;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class OrgArchive {

    private Integer vndbPid;

    @NotNull(message = "机构名 必传")
    private String orgName;

    private String mainImg;

    @NotNull(message = "简介 必传")
    private String introduction;

    private String country;

    private LocalDate birthday;

    @NotNull(message = "网站必传")
    @Valid
    private List<Website> website;

    @NotNull(message = "拓展名必传")
    @Valid
    private List<ExtensionName> extensionNames;
}
