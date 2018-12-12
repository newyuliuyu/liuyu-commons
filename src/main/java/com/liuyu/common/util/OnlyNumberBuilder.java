/**
 * Project Name:easytnt-commons
 * File Name:OnlyNumberBuilder.java
 * Package Name:com.ez.commons.util
 * Date:2016年4月28日下午4:39:21
 * Copyright (c) 2016, easytnt All Rights Reserved.
 */
package com.liuyu.common.util;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * ClassName: OnlyNumberBuilder <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2016年4月28日 下午4:39:21 <br/>
 *
 * @author 刘海林
 * @version v1.0
 * @since JDK 1.7+
 */
public class OnlyNumberBuilder {
    private static AtomicBoolean bool = new AtomicBoolean(true);

    public static String build() {

        Double random = Math.random() * Math.random() * 10000000;
        long millistime = System.currentTimeMillis();
        UUID uuid = new UUID(Double.doubleToLongBits(random), millistime);

        return uuid.getMostSignificantBits() + "";
    }
}
