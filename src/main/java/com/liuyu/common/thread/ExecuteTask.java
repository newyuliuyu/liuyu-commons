/** 
 * Project Name:easytnt-commons 
 * File Name:ExecuteTask.java 
 * Package Name:com.ez.commons.thread 
 * Date:2016年6月22日下午4:20:57 
 * Copyright (c) 2016, easytnt All Rights Reserved. 
 * 
 */
package com.liuyu.common.thread;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;

import java.util.concurrent.Callable;

/**
 * ClassName: ExecuteTask <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2016年6月22日 下午4:20:57 <br/>
 * 
 * @author 刘海林
 * @version v1.0
 * @since JDK 1.7+
 */
public final class ExecuteTask<T> {
	public ListenableFuture<T> exec(Callable<T> task) {
		ListeningExecutorService executor = ThreadExecutor.getInstance().getListeningExecutorService();
		ListenableFuture<T> result = executor.submit(task);
		return result;
	}
}
