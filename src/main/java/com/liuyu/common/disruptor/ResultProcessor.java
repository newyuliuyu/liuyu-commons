/** 
 * Project Name:easytnt-ez 
 * File Name:ResultProcessor.java 
 * Package Name:com.ez.framwork.fx.event 
 * Date:2016年8月9日下午5:32:06 
 * Copyright (c) 2016, easytnt All Rights Reserved. 
 * 
 */
package com.liuyu.common.disruptor;

/**
 * ClassName: ResultProcessor <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2016年8月9日 下午5:32:06 <br/>
 * 
 * @author 刘海林
 * @version v1.0
 * @since JDK 1.7+
 */
public interface ResultProcessor {
	public void fail();

	public void sucess();

	public void finallys();
}
