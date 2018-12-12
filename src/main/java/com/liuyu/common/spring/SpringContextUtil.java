/**
 * Project Name:easytnt-commons
 * File Name:SpringContextUtil.java
 * Package Name:com.easytnt.commons.spring
 * Date:2016年3月25日下午2:35:28
 * Copyright (c) 2016, easytnt All Rights Reserved.
 */
package com.liuyu.common.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * ClassName: SpringContextUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2016年3月25日 下午2:35:28 <br/>
 *
 * @author 刘海林
 * @version
 * @since JDK 1.7+
 */
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;


    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    public static <T> T getBean(Class clazz) {
        return (T) applicationContext.getBean(clazz);
    }

}