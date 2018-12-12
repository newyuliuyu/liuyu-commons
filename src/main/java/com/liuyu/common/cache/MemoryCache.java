/**
 * Project Name:easytnt-commons
 * File Name:MemoryCache.java
 * Package Name:com.ez.commons.cache
 * Date:2016年4月29日下午2:30:59
 * Copyright (c) 2016, easytnt All Rights Reserved.
 */
package com.liuyu.common.cache;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName: MemoryCache <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2016年4月29日 下午2:30:59 <br/>
 *
 * @author 刘海林
 * @version v1.0
 * @since JDK 1.7+
 */
public class MemoryCache {
    private static MemoryCache cache = new MemoryCache();
    private ConcurrentHashMap<String, Object> cacheData = new ConcurrentHashMap<>();

    public void put(String key, Object value) {
        cacheData.put(key, value);
    }

    public void remove(String key) {
        cacheData.remove(key);
    }

    public <T> T getValue(String key) {
        return (T) cacheData.get(key);
    }

    public Set<String> keys() {
        return cacheData.keySet();
    }

    public static MemoryCache instance() {
        return cache;
    }
}
