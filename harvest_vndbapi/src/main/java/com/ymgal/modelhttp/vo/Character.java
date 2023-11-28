package com.ymgal.modelhttp.vo;

import com.ymgal.modelhttp.vo.common.Image;
import com.ymgal.modelhttp.vo.common.Trait;
import com.ymgal.modelhttp.vo.common.Vns;
import lombok.Data;

import java.util.List;

/**
 * @Auther: lyl
 * @Date: 2023/10/16 10:55
 * @Description:
 */
@Data
public class Character {
    private String id;

    private String name;
    private String original;
    private List<String> aliases;

    private String description;
    private String blood_type;
    private String height;
    private String weight;
    private String bust;
    private String waist;
    private String hips;
    private String cup;
    private Integer age;
    private String birthday;
    private List<String> sex;
    private Image image;
    private List<Vns> vns;
    private List<Trait> traits;

}
