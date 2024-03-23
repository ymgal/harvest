package com.ymgal.harvest.vndb;


import com.fasterxml.jackson.core.type.TypeReference;
import com.ymgal.harvest.vndb.helper.HttpClientHelper;
import com.ymgal.harvest.vndb.helper.PathPostfixHelper;
import com.ymgal.harvest.vndb.modelhttp.PathPostfix;
import com.ymgal.harvest.vndb.modelhttp.RequestBody;
import com.ymgal.harvest.vndb.modelhttp.VndbFilter;
import com.ymgal.harvest.vndb.modelhttp.vo.Release;
import com.ymgal.harvest.vndb.modelhttp.vo.Vn;
import com.ymgal.harvest.vndb.modelhttp.vo.common.Exlink;
import com.ymgal.harvest.vndb.helper.JsonHelper;
import com.ymgal.harvest.vndb.model.VndbResponse;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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


    /**
     * @param:
     * @return:
     * @auther: lyl
     * @date: 2023/11/28 20:42
     * 功能描述: 通过静态页面获取Links
     */
    public static List<Exlink> getLinksByHtml(String url) {
        List<Exlink> exlinks = new ArrayList<>();
        try {
            String html = HttpClientHelper.getHtml(url);
            Document document = Jsoup.parse(html);

            Elements htmlLinks = document.select("td:contains(Links)");
            if (htmlLinks.size() == 0) return Collections.emptyList();

            if (htmlLinks.first() == null || htmlLinks.first().nextElementSibling() == null)
                return Collections.emptyList();

            Elements links = htmlLinks.first().nextElementSibling().select("a");
            if (links == null) Collections.emptyList();

            for (Element link : links) {

                Exlink exlink = new Exlink();
                // 获取文本
                exlink.setName(link.text());
                // 获取链接
                exlink.setUrl(link.attr("href"));
                exlinks.add(exlink);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return exlinks;
    }


}
