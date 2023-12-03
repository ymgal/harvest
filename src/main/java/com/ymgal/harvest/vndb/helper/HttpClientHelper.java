package com.ymgal.harvest.vndb.helper;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
/**
 * @Auther: lyl
 * @Date: 2023/10/13 09:19
 * @Description:
 */
public class HttpClientHelper {

    public static String getHtml(String url) throws IOException {

        // 创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建HttpGet请求
        HttpGet httpGet = new HttpGet(url);
        // 响应对象
        CloseableHttpResponse response = null;
        String html="";
        try {
            // 使用HttpClient发起请求
            response = httpClient.execute(httpGet);
            // 判断响应状态码是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                // 获取返回数据
                HttpEntity httpEntity = response.getEntity();
                html = EntityUtils.toString(httpEntity, "UTF-8");
            }
        } finally {
            // 释放连接
            if (response != null) {
                response.close();
            }
            httpClient.close();
            return html;
        }
    }


    /**
     * 向指定 URL 发送POST方法的请求
     * @param url 发送请求的 URL
     * @param jsonString 请求参数，请求参数应该是json字符串的形式。
     * @return 所代表远程资源的响应结果，对应的也是json字符串
     */
    public static String sendPost(String url, String jsonString){
        String body = "";
        try {
            //创建httpclient对象
            CloseableHttpClient client = HttpClients.createDefault();
            //创建post方式请求对象
            HttpPost httpPost = new HttpPost(url);

            //装填参数
            StringEntity s = new StringEntity(jsonString, "utf-8");
            s.setContentEncoding(new BasicHeader("contentType",
                    "application/json"));
            //设置参数到请求对象中
            httpPost.setEntity(s);
            System.out.println("请求地址：" + url);

            //设置header信息
            //指定报文头【Content-type】、【User-Agent】
            //httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

            //执行请求操作，并拿到结果（同步阻塞）
            CloseableHttpResponse response = client.execute(httpPost);
            //获取结果实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                //按指定编码转换结果实体为String类型
                body = EntityUtils.toString(entity, "UTF-8");
            }
            EntityUtils.consume(entity);
            //释放链接
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return body;
    }
}
