package com.liuyu.common.httpclient;

import org.apache.http.*;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * ClassName: HCUtils <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 17-8-8 上午10:32 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class HCUtils {
    private final Logger logger = LoggerFactory.getLogger(HCUtils.class);
    private CloseableHttpClient client;

    // 全局请求设置
    private RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
    // 创建cookie store的本地实例
    private CookieStore cookieStore = new BasicCookieStore();
    // 创建HttpClient上下文
    private HttpClientContext context = HttpClientContext.create();


    private HCUtils() {
        setContextCookieStore();
        HttpClientBuilder httpClientBuilder = HttpClients.custom()
                .setKeepAliveStrategy(createKeepAliveStrategy())
                .setRetryHandler(createRetryHandler())
                .setDefaultRequestConfig(globalConfig)
                .setDefaultCookieStore(cookieStore);
        client = httpClientBuilder.build();
    }


    public static HCUtils createDefault() {
        return new HCUtils();
    }

    public static HCUtils createLogin(HttpPost loginPost) {
        HCUtils hcUtils = new HCUtils();
        hcUtils.exec(loginPost);
        return hcUtils;
    }

    private void setContextCookieStore() {
        context.setCookieStore(cookieStore);
    }


    public RequestResult exec(HttpUriRequest httpUriRequest) {
        RequestResult requestResult = new RequestResult();
        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpUriRequest, context);
//            printResponse(response);
            if (response.getStatusLine().getStatusCode() == 404) {
                throw new RuntimeException("请求的地址不存在:url:" + httpUriRequest.getURI().toString());
            }
            logger.debug(response.getStatusLine().toString());
            HttpEntity httpEntity = response.getEntity();
            String content = EntityUtils.toString(httpEntity, Consts.UTF_8);
            requestResult.setContent(content);
            EntityUtils.consume(httpEntity);
        } catch (Exception e) {
            logger.error("执行请求失败", e);
            throw new RuntimeException("执行请求失败", e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (Exception e) {
                logger.error("关闭response失败", e);
                throw new RuntimeException("关闭response失败", e);
            }
        }
        return requestResult;
    }


    private void printResponse(CloseableHttpResponse response) {
        HeaderIterator it = response.headerIterator();
        logger.debug("************************************************");
        while (it.hasNext()) {
            logger.debug(it.next().toString());
        }
        logger.debug("************************************************");
    }


    private HttpRequestRetryHandler createRetryHandler() {
        return new StandardHttpRequestRetryHandler(3, false);
    }

    private ConnectionKeepAliveStrategy createKeepAliveStrategy() {
        ConnectionKeepAliveStrategy myStrategy = new ConnectionKeepAliveStrategy() {
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                // Honor 'keep-alive' header
                HeaderElementIterator it = new BasicHeaderElementIterator(
                        response.headerIterator(HTTP.CONN_KEEP_ALIVE));
                while (it.hasNext()) {
                    HeaderElement he = it.nextElement();
                    String param = he.getName();
                    String value = he.getValue();
                    if (value != null && param.equalsIgnoreCase("timeout")) {
                        try {
                            return Long.parseLong(value) * 1000;
                        } catch (NumberFormatException ignore) {
                        }
                    }
                }
                return 30 * 1000;
//                HttpHost target = (HttpHost) context.getAttribute(
//                        HttpClientContext.HTTP_TARGET_HOST);
//                if ("www.naughty-server.com".equalsIgnoreCase(target.getHostName())) {
//                    // Keep alive for 5 seconds only
//                    return 5 * 1000;
//                } else {
//                    // otherwise keep alive for 30 seconds
//                    return 30 * 1000;
//                }
            }

        };
        return myStrategy;
    }


    public void printCookie() {
        List<Cookie> cookies = cookieStore.getCookies();
        logger.debug("=============打印cookies=======================");
        for (Cookie cookie : cookies) {
            logger.debug("{}:{};{};{}", cookie.getName(), cookie.getValue(), cookie.getDomain(), cookie.getPath());
        }
        logger.debug("=============打印cookies=======================");
    }


    public void close() {
        try {
            client.close();
        } catch (Exception e) {
            throw new RuntimeException("关闭HttpClient出错", e);
        }
    }
}
