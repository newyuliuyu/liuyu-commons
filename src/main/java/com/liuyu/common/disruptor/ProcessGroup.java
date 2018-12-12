/** 
 * Project Name:easytnt-commons 
 * File Name:ProcessGroup.java 
 * Package Name:com.ez.commons.data.processing 
 * Date:2016年4月28日下午5:57:28 
 * Copyright (c) 2016, easytnt All Rights Reserved. 
 * 
 */
package com.liuyu.common.disruptor;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * ClassName: ProcessGroup <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2016年4月28日 下午5:57:28 <br/>
 * 
 * @author 刘海林
 * @version v1.0
 * @since JDK 1.7+
 */
public class ProcessGroup {
	private ImmutableList<Processor> processors;

	public ProcessGroup setProcessor(List<Processor> processors) {
		Preconditions.checkNotNull(processors, "数据处理不能空");
		this.processors = ImmutableList.copyOf(processors);
		return this;
	}

	public ProcessGroup setProcessor(Processor... processors) {
		Preconditions.checkNotNull(processors, "数据处理不能空");
		this.processors = ImmutableList.copyOf(processors);
		return this;
	}

	public List<Processor> getProcessors() {
		return processors;
	}
}
