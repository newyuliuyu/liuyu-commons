/** 
 * Project Name:easytnt-commons 
 * File Name:ThreadExecutor.java 
 * Package Name:com.ez.commons.thread 
 * Date:2016年3月30日上午10:46:44 
 * Copyright (c) 2016, easytnt All Rights Reserved. 
 * 
 */
package com.liuyu.common.thread;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName: ThreadExecutor <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2016年3月30日 上午10:46:44 <br/>
 * 
 * @author 刘海林
 * @version v1.0
 * @since JDK 1.7+
 */
public class ThreadExecutor {
	private static ThreadExecutor threadExecutor = new ThreadExecutor();
	private ExecutorService executorService;

	private ThreadExecutor() {
		executorService = Executors.newCachedThreadPool(createThreadFactory());
	}

	public static ThreadExecutor getInstance() {
		return threadExecutor;
	}

	public ExecutorService getExecutorService() {
		return executorService;
	}

	public ListeningExecutorService getListeningExecutorService() {
		return MoreExecutors.listeningDecorator(executorService);
	}

	public static ThreadFactory createThreadFactory() {
		return new MyThreadFactory();
	}

	private static class MyThreadFactory implements ThreadFactory {
		private static final AtomicInteger poolNo = new AtomicInteger();

		@Override
		public Thread newThread(Runnable runner) {
			SecurityManager manager = System.getSecurityManager();
			ThreadGroup group = manager != null ? manager.getThreadGroup() : Thread.currentThread().getThreadGroup();
			Thread thread = new Thread(group, runner, "EasyTNT-Thread-" + poolNo.getAndIncrement() + "-");
			if (thread.isDaemon()) {
				thread.setDaemon(Boolean.FALSE);
			}
			if (thread.getPriority() != Thread.NORM_PRIORITY) {
				thread.setPriority(Thread.NORM_PRIORITY);
			}
			return thread;
		}

	}
}
