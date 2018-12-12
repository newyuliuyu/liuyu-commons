/** 
 * Project Name:easydata.commons 
 * File Name:FreemarkerStaticModels.java 
 * Package Name:com.ez.data.commons.freemarker 
 * Date:2017年4月10日下午5:31:16 
 * Copyright (c) 2017, easytnt All Rights Reserved. 
 * 
 */
package com.liuyu.common.freemarker;

import com.liuyu.common.util.ThrowableToString;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModelException;
import freemarker.template.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: FreemarkerStaticModels <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年4月10日 下午5:31:16 <br/>
 * 
 * @author 刘海林
 * @version v1.0
 * @since JDK 1.7+
 */
public class FreemarkerStaticModels extends HashMap<String, Object> {
	private static Logger logger = LoggerFactory.getLogger(FreemarkerStaticModels.class);

	public FreemarkerStaticModels(Map<String, String> classMap) {
		for (String key : classMap.keySet()) {
			put(key, getModel(classMap.get(key)));
		}
	}

	private TemplateHashModel getModel(String packageName) {
		BeansWrapper wrapper = new BeansWrapperBuilder(new Version("2.3.23")).build();
		TemplateHashModel staticModels = wrapper.getStaticModels();
		TemplateHashModel fileStatics;
		try {
			fileStatics = (TemplateHashModel) staticModels.get(packageName);
			return fileStatics;
		} catch (TemplateModelException e) {
			logger.error(ThrowableToString.cleanExceptionString(e));
		}
		return null;
	}
}
