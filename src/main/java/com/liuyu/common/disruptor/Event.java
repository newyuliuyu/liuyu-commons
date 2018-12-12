/** 
 * Project Name:easytnt-ez 
 * File Name:EzEvent.java 
 * Package Name:com.ez.framwork.fx.readdata 
 * Date:2016年8月9日上午9:37:18 
 * Copyright (c) 2016, easytnt All Rights Reserved. 
 * 
 */
package com.liuyu.common.disruptor;

/**
 * ClassName: EzEvent <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2016年8月9日 上午9:37:18 <br/>
 * 
 * @author 刘海林
 * @version v1.0
 * @since JDK 1.7+
 */
public class Event {
	private Object obj;
	private boolean hasError = false; //记录disruptor处理过程中产生的错误信息
	private boolean hasWarn = false;
	private String message;
	private int rowIdx = 0;

	public int getRowIdx() {
		return rowIdx;
	}

	public void setRowIdx(int rowIdx) {
		this.rowIdx = rowIdx;
	}

	public boolean isHasError() {
		return hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

	public boolean isHasWarn() {
		return hasWarn;
	}

	public void setHasWarn(boolean hasWarn) {
		this.hasWarn = hasWarn;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void set(Object obj) {
		this.obj = obj;
	}

	public <T> T get() {
		return (T) obj;
	}

	public void copy(Event from) {
		obj = from.obj;
		rowIdx = from.rowIdx;
		hasError = false;
		hasWarn = false;
		message = "";
	}
}
