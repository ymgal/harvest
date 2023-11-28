package com.ymgal.modelhttp.vo;

import com.ymgal.modelhttp.vo.common.Image;
import com.ymgal.modelhttp.vo.common.Producer;
import com.ymgal.modelhttp.vo.common.Tag;
import com.ymgal.modelhttp.vo.common.Title;
import lombok.Data;

import java.util.List;

@Data
public class Vn {
    private String id;

    private String title;
    private String alttitle;
    private List<String> aliases;
    private String olang;
    private String devstatus;
    private String released;
    private List<String> languages;
    private List<String> platforms;
    private String length;
    private String length_minutes;
    private String length_votes;
    private String description;
    private String rating;
    private String votecount;
    private Image image;
    private List<Title> titles;
    private List<Tag> tags;
    private List<Producer> developers;
}
