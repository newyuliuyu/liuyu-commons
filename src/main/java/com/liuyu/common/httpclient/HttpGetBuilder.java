package com.liuyu.common.httpclient;

import com.google.common.collect.Maps;
import org.apache.http.client.methods.HttpGet;

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
public class HttpGetBuilder {

    private HttpGet get;
    private String url;
    private Map<String, String> header = Maps.newHashMap();
    private Map<String, String> params = Maps.newHashMap();

    public static HttpGetBuilder create(String url) {
        return new HttpGetBuilder(url);
    }

    private HttpGetBuilder(String url) {
        this.url = url;
    }

    public HttpGetBuilder addHeader(String name, String value) {
        header.put(name, value);
        return this;
    }

    public HttpGetBuilder addParam(String name, String value) {
        params.put(name, value);
        return this;
    }

    private void buildURL() {
        StringBuilder sb = new StringBuilder(url);
        if (!params.isEmpty()) {
            sb.append("?");
        }
        int idx = 0;
        for (String name : params.keySet()) {
            if (idx++ != 0) {
                sb.append("&");
            }
            sb.append(name + "=" + params.get(name));
        }
        this.url = sb.toString();
    }

    private void buildHeader() {
        for (String name : header.keySet()) {
            get.addHeader(name, header.get(name));
        }
    }

    public HttpGet build() {
        buildURL();
        get = new HttpGet(url);
        buildHeader();
        return get;
    }
}
