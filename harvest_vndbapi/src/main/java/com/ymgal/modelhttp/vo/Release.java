package com.ymgal.modelhttp.vo;

import com.ymgal.modelhttp.vo.common.Exlink;
import com.ymgal.modelhttp.vo.common.Language;
import com.ymgal.modelhttp.vo.common.Media;
import com.ymgal.modelhttp.vo.common.Vns;
import lombok.Data;

import java.util.List;

/**
 * @Auther: lyl
 * @Date: 2023/10/16 10:37
 * @Description:
 */
@Data
public class Release {
    private String id;

    private String title;
    private String alttitle;
    private List<String> platforms;
    private String released;
    private Integer minage;
    private Boolean patch;
    private Boolean freeware;
    private Boolean uncensored;
    private Boolean official;
    private Boolean has_ero;
    private List<Integer> resolution;
    private String engine;
    private Integer voiced;
    private String notes;
    private String gtin;
    private String catalog;
    private List<Language> languages;
    private List<Media> media;
    private List<Vns> vns;

    private List<Producer> producers;
    private List<Exlink> extlinks;
}
