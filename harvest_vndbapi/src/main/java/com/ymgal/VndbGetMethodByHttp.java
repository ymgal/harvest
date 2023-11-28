package com.ymgal;


import com.fasterxml.jackson.core.type.TypeReference;
import com.ymgal.helper.HttpClientHelper;
import com.ymgal.helper.JsonHelper;
import com.ymgal.helper.PathPostfixHelper;
import com.ymgal.model.VndbResponse;
import com.ymgal.modelhttp.PathPostfix;
import com.ymgal.modelhttp.RequestBody;
import com.ymgal.modelhttp.VndbFilter;
import com.ymgal.modelhttp.vo.Release;
import com.ymgal.modelhttp.vo.Vn;

/**
 * @Auther: lyl
 * @Date: 2023/10/13 09:13
 * @Description:
 */

public class VndbGetMethodByHttp {


    private final static String baseurl = "https://api.vndb.org/kana";

    /**
     * 请求VNDB接口，将返回的json转化为java对象
     *
     * @param postfix 枚举  URL后缀。如：https://api.vndb.org/kana/vn 中的/vn
     * @param filter  过滤器  如：id = 17
     * @return 泛型的java对象
     */
    public static <T> T getInfoApi(PathPostfix postfix, VndbFilter filter, TypeReference<T> typeReference) {
        //Class<?> tClass = Vn.class;
        //根据postfix获取fields
        String fields = PathPostfixHelper.getFields(postfix);

        //url赋值
        String url = baseurl + postfix.getPostfix();

        //body
        RequestBody body = new RequestBody();
        body.setFilters(filter.toFormatString());
        body.setFields(fields);

        //print
        System.out.println("Url " + url);
        System.out.println("Filter " + filter.toFormatString());
        System.out.println("Field " + fields);

        String jsonstr = HttpClientHelper.sendPost(url, JsonHelper.serialize(body));
        return JsonHelper.parse(jsonstr, typeReference);
    }

    public static VndbResponse<Vn> GetVisualNovel(VndbFilter vndbFilter) {
        return VndbGetMethodByHttp.getInfoApi(PathPostfix.VN, vndbFilter, new TypeReference<VndbResponse<Vn>>() {
        });
    }

    public static VndbResponse<Release> getRelease(VndbFilter vndbFilter) {
        return VndbGetMethodByHttp.getInfoApi(PathPostfix.RELEASE, vndbFilter, new TypeReference<VndbResponse<Release>>() {
        });
    }

}
