/** 
 * Project Name:easydata.commons 
 * File Name:Week.java 
 * Package Name:com.ez.commons.util 
 * Date:2017年3月15日上午10:57:42 
 * Copyright (c) 2017, easytnt All Rights Reserved. 
 * 
 */
package com.liuyu.common.util;

/**
 * ClassName: Week <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年3月15日 上午10:57:42 <br/>
 * 
 * @author 刘海林
 * @version v1.0
 * @since JDK 1.7+
 */
public enum Week {
	MONDAY("星期一", "Monday", "Mon.", 1), TUESDAY("星期二", "Tuesday", "Tues.", 2), WEDNESDAY("星期三", "Wednesday", "Wed.",
			3), THURSDAY("星期四", "Thursday", "Thur.", 4), FRIDAY("星期五", "Friday", "Fri.",
					5), SATURDAY("星期六", "Saturday", "Sat.", 6), SUNDAY("星期日", "Sunday", "Sun.", 7);

	String name_cn;
	String name_en;
	String name_enShort;
	int		number;

	Week(String name_cn, String name_en, String name_enShort, int number) {
		this.name_cn = name_cn;
		this.name_en = name_en;
		this.name_enShort = name_enShort;
		this.number = number;
	}

	public String getChineseName() {
		return name_cn;
	}

	public String getName() {
		return name_en;
	}

	public String getShortName() {
		return name_enShort;
	}

	public int getNumber() {
		return number;
	}
}
