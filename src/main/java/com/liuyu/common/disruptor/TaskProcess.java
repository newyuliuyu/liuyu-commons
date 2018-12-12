/** 
 * Project Name:easydata.commons 
 * File Name:TaskProcess.java 
 * Package Name:com.ez.data.commons.disruptor 
 * Date:2017年4月25日上午10:26:22 
 * Copyright (c) 2017, easytnt All Rights Reserved. 
 * 
 */
package com.liuyu.common.disruptor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ClassName: TaskProcess <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年4月25日 上午10:26:22 <br/>
 * 
 * @author 刘海林
 * @version v1.0
 * @since JDK 1.7+
 */
public class TaskProcess {
	private int								total;
	private int								completed;
	private String text;
	private boolean							finished;
	private boolean							hasError		= false;
	private boolean							hasWarn			= false;
	private CopyOnWriteArrayList<String> errorMessage	= new CopyOnWriteArrayList<>();
	private CopyOnWriteArrayList<String> warnMessage		= new CopyOnWriteArrayList<>();

	public TaskProcess(int total, String text) {
		this.total = total;
		this.text = text;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCompleted() {
		return completed;
	}

	public void setCompleted(int completed) {
		this.completed = completed;
	}

	public int getPercent() {
		Double value = completed * 1.0 / total;
		return value.intValue();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public void addErrorMessage(List<String> message) {
		hasError = true;
		errorMessage.addAll(message);
	}

	public void addErrorMessage(String message) {
		hasError = true;
		errorMessage.add(message);
	}

	public void addWarnMessage(List<String> message) {
		hasWarn = true;
		warnMessage.addAll(message);
	}

	public void addWarnMessage(String message) {
		hasWarn = true;
		warnMessage.add(message);
	}

	public List<String> getErrorMessage() {
		ArrayList<String> message = new ArrayList<>();
		message.addAll(errorMessage);
		errorMessage.clear();
		return message;
	}

	public boolean isHasError() {
		return hasError;
	}

	public boolean isHasWarn() {
		return hasWarn;
	}

	public void setWarnMessage(List<String> warnMessage) {
		this.warnMessage.addAll(warnMessage);
		if (!warnMessage.isEmpty()) {
			hasWarn = true;
		}
	}

	public void setErrorMessage(List<String> errorMessage) {
		this.errorMessage.addAll(errorMessage);
		if (!errorMessage.isEmpty()) {
			hasError = true;
		}
	}

	public List<String> getWarnMessage() {
		ArrayList<String> message = new ArrayList<>();
		message.addAll(warnMessage);
		warnMessage.clear();
		return message;
	}
}
