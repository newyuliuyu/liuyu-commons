/** 
 * Project Name:easytnt-commons 
 * File Name:ThrowableParser.java 
 * Package Name:com.easytnt.commons.exception 
 * Date:2016年3月25日下午2:40:57 
 * Copyright (c) 2016, easytnt All Rights Reserved. 
 * 
 */
package com.liuyu.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * ClassName: ThrowableParser <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2016年3月25日 下午2:40:57 <br/>
 * 
 * @author 刘海林
 * @version
 * @since JDK 1.7+
 */
public class ThrowableToString {
	public static String toString(Throwable t) {
		if (t == null)
			return "Throwable is null";
		StringWriter swriter = new StringWriter();
		PrintWriter pwriter = new PrintWriter(swriter);
		t.printStackTrace(pwriter);
		String s = swriter.toString();
		pwriter.close();
		return s;
	}

	public static String formatHtml(Throwable t) {
		String msg = toString(t);
		return msg.replaceAll("\n\t", "<br/>");
	}

	public static String cleanExceptionString(Throwable t) {
		String msg = t.getMessage();
		if (msg != null && msg.contains("Exception")) {
			return msg.substring(msg.indexOf("Exception:") + "Exception:".length(), msg.length());
		} else {
			return msg;
		}
	}
}
