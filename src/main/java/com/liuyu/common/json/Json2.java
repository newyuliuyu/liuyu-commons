/**
 * Project Name:easytnt-commons
 * File Name:Json.java
 * Package Name:com.ez.commons.util
 * Date:2016年5月5日下午7:38:10
 * Copyright (c) 2016, easytnt All Rights Reserved.
 */
package com.liuyu.common.json;

import com.alibaba.fastjson.JSON;

import java.util.Map;

/**
 * ClassName: Json <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2016年5月5日 下午7:38:10 <br/>
 *
 * @author 刘海林
 * @version v1.0
 * @since JDK 1.7+
 */
public final class Json2 {
    public static String toJson(Object src) {
        String json = JSON.toJSONString(src);
        return json;
    }

    /**
     * 判断字符串是否是json格式
     *
     * @param str
     * @return
     */
    public static boolean isJson(String str) {
        boolean result = false;
        try {
            Json2.fromJson(str, Map.class);
            result = true;
        } catch (Exception e) {
        }

        return result;
    }

    public static <T> T fromJson(String json, Class<T> type) {
        T obj = JSON.parseObject(json, type);
        return obj;
    }

    public static Object parse(String json) {
        Object obj = JSON.parse(json);
        return obj;
    }

}
