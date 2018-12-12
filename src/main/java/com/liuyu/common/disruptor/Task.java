/**
 * Project Name:easytnt-ez
 * File Name:EzTask.java
 * Package Name:com.ez.framwork.fx.event
 * Date:2016年8月9日上午11:46:28
 * Copyright (c) 2016, easytnt All Rights Reserved.
 */
package com.liuyu.common.disruptor;

/**
 * ClassName: EzTask <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2016年8月9日 上午11:46:28 <br/>
 *
 * @author 刘海林
 * @version v1.0
 * @since JDK 1.7+
 */
public interface Task {
    public int getTaskTotlaNum();

    public boolean next();

    public <T> T get();
}
