package com.liuyu.common.httpclient;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.liuyu.common.json.Json2;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * ClassName: HttpGetBuilder <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 17-8-8 上午11:04 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class HttpPostBuilder {

    private HttpPost post;
    private List<NameValuePair> formData = Lists.newArrayList();
    private List<NameValuePair> files = Lists.newArrayList();

    public static HttpPostBuilder create(String url) {
        return new HttpPostBuilder(url);
    }

    private HttpPostBuilder(String url) {
        post = new HttpPost(url);
    }

    public HttpPostBuilder addHeader(String name, String value) {
        post.addHeader(name, value);
        return this;
    }

    public HttpPostBuilder addFormData(String name, String value) {
        formData.add(new BasicNameValuePair(name, value));
        return this;
    }

    public HttpPostBuilder addFile(String name, String filepath) {
        files.add(new BasicNameValuePair(name, filepath));
        return this;
    }


    private HttpEntity createForm() {
        return new UrlEncodedFormEntity(formData, Consts.UTF_8);
    }

    private HttpEntity createFileForm() {
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        for (NameValuePair file : files) {
            File tmp = new File(file.getValue());
            entityBuilder.addBinaryBody(file.getName(), tmp);
        }
        for (NameValuePair nameValuePair : formData) {
            entityBuilder.addTextBody(nameValuePair.getName(), nameValuePair.getValue());
        }

        return entityBuilder.build();

    }

    public HttpPost build() {
        HttpEntity reqEntity = null;
        if (!files.isEmpty()) {
            reqEntity = createFileForm();
        } else {
            reqEntity = createForm();
        }
        post.setEntity(reqEntity);
        return post;
    }

    public HttpPost buildJson() {
        String json = toJsonData();
        setJsonPost(json);
        return post;
    }

    public HttpPost buildJson(String jsonData) {
        setJsonPost(jsonData);
        return post;
    }

    private String toJsonData() {
        Map<String, String> data = Maps.newHashMap();
        for (NameValuePair nameValuePair : formData) {
            data.put(nameValuePair.getName(), nameValuePair.getValue());
        }
        String json = Json2.toJson(data);
        return json;
    }

    private void setJsonPost(String json) {

        StringEntity entity = new StringEntity(json, "utf-8");// 解决中文乱码问题
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        post.setEntity(entity);
        post.addHeader("Content-type", "application/json; charset=utf-8");
        post.setHeader("Accept", "application/json");
    }


}
