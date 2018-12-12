/** 
 * Project Name:easytnt-ez 
 * File Name:Processor.java 
 * Package Name:com.ez.framwork.fx.readdata 
 * Date:2016年8月8日下午3:03:21 
 * Copyright (c) 2016, easytnt All Rights Reserved. 
 * 
 */
package com.liuyu.common.disruptor;

/**
 * ClassName: Processor <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2016年8月8日 下午3:03:21 <br/>
 * 
 * @author 刘海林
 * @version v1.0
 * @since JDK 1.7+
 */
public interface Processor<T> {
	public Class<T> getObjClazz();

	public void process(T data);
}
