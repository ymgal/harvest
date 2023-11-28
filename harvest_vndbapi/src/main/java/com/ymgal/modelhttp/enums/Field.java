package com.ymgal.modelhttp.enums;

/**
 * @Auther: lyl
 * @Date: 2023/10/13 19:58
 * @Description:
 */
public class Field {

    //VN档案
    public static final String vn = "id,title,alttitle,titles{lang,title,latin,official,main}," +
            "aliases,olang,devstatus,released,languages,platforms,image{id,url,dims,sexual,violence,votecount}," +
            "length,length_minutes,length_votes,description,rating,votecount,screenshots{thumbnail,thumbnail_dims},tags{rating,spoiler,lie}," +
            "developers{id,name,original,aliases,lang,type,description}";

    //发售信息
    public static final String release = "id,title,alttitle,languages{lang,title,latin,main},platforms,media{medium,qty},vns{rtype}," +
            "producers{developer,publisher},released,minage,patch,freeware,uncensored,official,has_ero,resolution,engine,voiced,notes," +
            "gtin,catalog,extlinks{id,url,label,name}";


    //制作公司
    public static final String producer = "id,name,original,aliases,lang,type,descriptio";

    public static final String character = "id,name,original,aliases,description,image{id,url,dims,sexual,violence,votecount}" +
            ",blood_type,height,weight,bust,waist,hips,cup,age,birthday,sex,vns{spoiler,role},traits{spoiler,lie}";

}



