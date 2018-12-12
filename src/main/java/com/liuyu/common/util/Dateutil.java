/** 
 * Project Name:easydata.commons 
 * File Name:DataUtil.java 
 * Package Name:com.ez.commons.util 
 * Date:2017年3月15日上午9:42:47 
 * Copyright (c) 2017, easytnt All Rights Reserved. 
 * 
 */
package com.liuyu.common.util;

import com.google.common.base.Throwables;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/** 
 * ClassName: DataUtil <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason: TODO ADD REASON(可选). <br/> 
 * date: 2017年3月15日 上午9:42:47 <br/> 
 * 
 * @author 刘海林
 * @version v1.0
 * @since JDK 1.7+
 */

/**
 * 日期处理工具类
 * 
 * @author Lee
 */

public class Dateutil {
	// ~ Static fields/initializers
	// =============================================

	private static Logger	log					= LoggerFactory.getLogger(Dateutil.class);
	private static String defaultDatePattern	= null;
	private static Calendar cale				= Calendar.getInstance();

	public static final String TS_FORMAT = Dateutil.getDatePattern() + "HH:mm:ss.S";
	// ~ Methods
	// ================================================================

	public Dateutil() {
	}

	/**
	 * 获得服务器当前日期及时间，以格式为：yyyy-MM-dd HH:mm:ss的日期字符串形式返回
	 */
	public static String getDateTime() {
		return getDateTime(DateStyle.YYYY_MM_DD_HH_MM_SS, cale.getTime());
	}

	/**
	 * 获得服务器当前日期，以格式为：yyyy-MM-dd的日期字符串形式返回
	 */
	public static String getDate() {
		return getDateTime(DateStyle.YYYY_MM_DD, cale.getTime());
	}

	/**
	 * 获得服务器当前时间，以格式为：HH:mm:ss的日期字符串形式返回
	 */
	public static String getTime() {
		return getDateTime(DateStyle.HH_MM_SS, cale.getTime());
	}

	/**
	 * 统计时开始日期的默认值, 今年的开始时间
	 */
	public static String getStartDate() {
		return getYear() + "-01-01";
	}

	/**
	 * 统计时结束日期的默认值
	 */
	public static String getEndDate() {
		return getDate();
	}

	/**
	 * 获得服务器当前日期的年份
	 */
	public static String getYear() {
		return String.valueOf(cale.get(Calendar.YEAR));
	}

	public static String getYear(Date date) {
		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		return String.valueOf(cale.get(Calendar.YEAR));
	}

	/**
	 * 获得服务器当前日期的月份
	 */
	public static String getMonth() {
		java.text.DecimalFormat df = new java.text.DecimalFormat();
		df.applyPattern("00");
		return df.format((cale.get(Calendar.MONTH) + 1));
	}

	public static String getMonth(Date date) {
		java.text.DecimalFormat df = new java.text.DecimalFormat();
		df.applyPattern("00");
		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		return df.format((cale.get(Calendar.MONTH) + 1));
	}

	/**
	 * 获得服务器在当前月中天数
	 */
	public static String getDay() {
		return String.valueOf(cale.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * 比较两个日期相差的天数, 第一个日期要比第二个日期要晚
	 */
	public static int getMargin(String date1, String date2) {
		SimpleDateFormat sdf = new SimpleDateFormat(DateStyle.YYYY_MM_DD.getValue());
		ParsePosition pos = new ParsePosition(0);
		ParsePosition pos1 = new ParsePosition(0);
		Date dt1 = sdf.parse(date1, pos);
		Date dt2 = sdf.parse(date2, pos1);
		long l = dt1.getTime() - dt2.getTime();
		int margin = (int) (l / (24 * 60 * 60 * 1000));
		return margin;
	}

	/**
	 * 比较两个日期相差的天数，格式不一样 第一个日期要比第二个日期要晚
	 */
	public static double getDoubleMargin(String date1, String date2) {
		SimpleDateFormat sdf = new SimpleDateFormat(DateStyle.YYYY_MM_DD_HH_MM_SS.getValue());
		ParsePosition pos = new ParsePosition(0);
		ParsePosition pos1 = new ParsePosition(0);
		Date dt1 = sdf.parse(date1, pos);
		Date dt2 = sdf.parse(date2, pos1);
		long l = dt1.getTime() - dt2.getTime();
		double margin = (l / (24 * 60 * 60 * 1000.00));
		return margin;
	}

	/**
	 * 比较两个日期相差的月数
	 */
	public static int getMonthMargin(String date1, String date2) {
		int margin;
		margin = (Integer.parseInt(date2.substring(0, 4)) - Integer.parseInt(date1.substring(0, 4))) * 12;
		margin += (Integer.parseInt(date2.substring(4, 7).replaceAll("-0", "-"))
				- Integer.parseInt(date1.substring(4, 7).replaceAll("-0", "-")));
		return margin;
	}

	/**
	 * 返回日期加X天后的日期
	 */
	public static String addDay(String date, int i) {
		SimpleDateFormat sdf = new SimpleDateFormat(DateStyle.YYYY_MM_DD.getValue());
		GregorianCalendar gCal = new GregorianCalendar(Integer.parseInt(date.substring(0, 4)),
				Integer.parseInt(date.substring(5, 7)) - 1, Integer.parseInt(date.substring(8, 10)));
		gCal.add(GregorianCalendar.DATE, i);
		return sdf.format(gCal.getTime());
	}

	/**
	 * 返回日期加X月后的日期
	 */
	public static String addMonth(String date, int i) {
		SimpleDateFormat sdf = new SimpleDateFormat(DateStyle.YYYY_MM_DD.getValue());
		GregorianCalendar gCal = new GregorianCalendar(Integer.parseInt(date.substring(0, 4)),
				Integer.parseInt(date.substring(5, 7)) - 1, Integer.parseInt(date.substring(8, 10)));
		gCal.add(GregorianCalendar.MONTH, i);
		return sdf.format(gCal.getTime());
	}

	/**
	 * 返回日期加X年后的日期
	 */
	public static String addYear(String date, int i) {
		SimpleDateFormat sdf = new SimpleDateFormat(DateStyle.YYYY_MM_DD.getValue());
		GregorianCalendar gCal = new GregorianCalendar(Integer.parseInt(date.substring(0, 4)),
				Integer.parseInt(date.substring(5, 7)) - 1, Integer.parseInt(date.substring(8, 10)));
		gCal.add(GregorianCalendar.YEAR, i);
		return sdf.format(gCal.getTime());
	}

	/**
	 * 返回某年某月中的最大天
	 */
	public static int getMaxDay(String year, String month) {
		int day = 0;
		try {
			int iyear = Integer.parseInt(year);
			int imonth = Integer.parseInt(month);
			if (imonth == 1 || imonth == 3 || imonth == 5 || imonth == 7 || imonth == 8 || imonth == 10
					|| imonth == 12) {
				day = 31;
			} else if (imonth == 4 || imonth == 6 || imonth == 9 || imonth == 11) {
				day = 30;
			} else if ((0 == (iyear % 4)) && (0 != (iyear % 100)) || (0 == (iyear % 400))) {
				day = 29;
			} else {
				day = 28;
			}
			return day;
		} catch (Exception e) {
			log.debug("DateUtil.getMonthDay():" + e.toString());
			return 1;
		}
	}

	/**
	 * 格式化日期
	 */
	@SuppressWarnings("static-access")
	public String rollDate(String orgDate, int Type, int Span) {
		try {
			String temp = "";
			int iyear, imonth, iday;
			int iPos = 0;
			char seperater = '-';
			if (orgDate == null || orgDate.length() < 6) {
				return "";
			}

			iPos = orgDate.indexOf(seperater);
			if (iPos > 0) {
				iyear = Integer.parseInt(orgDate.substring(0, iPos));
				temp = orgDate.substring(iPos + 1);
			} else {
				iyear = Integer.parseInt(orgDate.substring(0, 4));
				temp = orgDate.substring(4);
			}

			iPos = temp.indexOf(seperater);
			if (iPos > 0) {
				imonth = Integer.parseInt(temp.substring(0, iPos));
				temp = temp.substring(iPos + 1);
			} else {
				imonth = Integer.parseInt(temp.substring(0, 2));
				temp = temp.substring(2);
			}

			imonth--;
			if (imonth < 0 || imonth > 11) {
				imonth = 0;
			}

			iday = Integer.parseInt(temp);
			if (iday < 1 || iday > 31)
				iday = 1;

			Calendar orgcale = Calendar.getInstance();
			orgcale.set(iyear, imonth, iday);
			temp = this.rollDate(orgcale, Type, Span);
			return temp;
		} catch (Exception e) {
			return "";
		}
	}

	public static String rollDate(Calendar cal, int Type, int Span) {
		SimpleDateFormat sdf = new SimpleDateFormat(DateStyle.YYYY_MM_DD.getValue());
		try {
			String temp = "";
			Calendar rolcale;
			rolcale = cal;
			rolcale.add(Type, Span);
			temp = sdf.format(rolcale.getTime());
			return temp;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 
	 * 返回默认的日期格式
	 * 
	 */
	public static synchronized DateStyle getDatePattern() {
		return DateStyle.YYYY_MM_DD;
	}

	/**
	 * 将指定日期按默认格式进行格式代化成字符串后输出如：yyyy-MM-dd
	 */
	public static final String getDate(Date aDate) {
		SimpleDateFormat df = new SimpleDateFormat(DateStyle.YYYY_MM_DD.getValue());
		String returnValue = df.format(aDate);
		return (returnValue);
	}

	public static final String getDate(Long time) {
		SimpleDateFormat df = new SimpleDateFormat(DateStyle.YYYY_MM_DD.getValue());
		Date aDate = new Date();
		aDate.setTime(time);
		String returnValue = df.format(aDate);
		return (returnValue);
	}

	/**
	 * 取得给定日期的时间字符串，格式为当前默认时间格式
	 */
	public static String getTimeNow(Date theTime) {
		return getDateTime(DateStyle.HH_MM, theTime);
	}

	/**
	 * 取得当前时间的Calendar日历对象
	 */
	public Calendar getToday() {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(DateStyle.YYYY_MM_DD.getValue());
		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));
		return cal;
	}

	/**
	 * 将日期类转换成指定格式的字符串形式
	 */
	public static final String getDateTime(DateStyle aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";
		if (aDate == null) {
			log.error("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask.getValue());
			returnValue = df.format(aDate);
		}
		return (returnValue);
	}

	/**
	 * 将指定的日期转换成默认格式的字符串形式
	 */
	public static final String convertDateToString(Date aDate) {
		return getDateTime(getDatePattern(), aDate);
	}

	/**
	 * 将日期字符串按指定格式转换成日期类型
	 * 
	 * @param aMask
	 *            指定的日期格式，如:yyyy-MM-dd
	 * @param strDate
	 *            待转换的日期字符串
	 */

	public static final Date convertStringToDate(DateStyle aMask, String strDate) {
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(aMask.getValue());

		if (log.isDebugEnabled()) {
			log.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
		}
		try {
			date = df.parse(strDate);
		} catch (Exception e) {
			log.error("ParseException: " + e);
			Throwables.propagate(new RuntimeException(String.format("日期%s转换成格式%出错", strDate, aMask), e));
		}
		return (date);
	}

	/**
	 * 将日期字符串按默认格式转换成日期类型
	 */
	public static Date convertStringToDate(String strDate) {
		Date aDate = convertStringToDate(getDatePattern(), strDate);
		return aDate;
	}

	/**
	 * 返回一个JAVA简单类型的日期字符串
	 */
	public static String getSimpleDateFormat() {
		SimpleDateFormat formatter = new SimpleDateFormat();
		String NDateTime = formatter.format(new Date());
		return NDateTime;
	}

	/**
	 * 将两个字符串格式的日期进行比较
	 * 
	 * @param last
	 *            要比较的第一个日期字符串
	 * @param now
	 *            要比较的第二个日期格式字符串
	 * @return true(last 在now 日期之前),false(last 在now 日期之后)
	 */
	public static boolean compareTo(String last, String now) {
		Date temp1 = convertStringToDate(DateStyle.YYYY_MM_DD_HH_MM_SS, last);
		Date temp2 = convertStringToDate(DateStyle.YYYY_MM_DD_HH_MM_SS, now);
		if (temp1.after(temp2)) {
			return false;
		} else if (temp1.before(temp2)) {
			return true;
		} else {
			return false;
		}
	}

	protected Object convertToDate(Class type, Object value) {
		DateFormat df = new SimpleDateFormat(TS_FORMAT);
		if (value instanceof String) {
			try {
				if (StringUtils.isEmpty(value.toString())) {
					return null;
				}
				return df.parse((String) value);
			} catch (Exception pe) {
				throw new ConversionException("Error converting String to Timestamp");
			}
		}
		throw new ConversionException("Could not convert " + value.getClass().getName() + " to " + type.getName());
	}

	/**
	 * 为查询日期添加最小时间
	 * 
	 * @param 目标类型Date
	 * @param 转换参数Date
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static Date addStartTime(Date param) {
		Date date = param;
		try {
			date.setHours(0);
			date.setMinutes(0);
			date.setSeconds(0);
			return date;
		} catch (Exception ex) {
			return date;
		}
	}

	/**
	 * 为查询日期添加最大时间
	 * 
	 * @param 目标类型Date
	 * @param 转换参数Date
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static Date addEndTime(Date param) {
		Date date = param;
		try {
			date.setHours(23);
			date.setMinutes(59);
			date.setSeconds(0);
			return date;
		} catch (Exception ex) {
			return date;
		}
	}

	/**
	 * 返回系统现在年份中指定月份的天数
	 * 
	 * @param 月份month
	 * @return 指定月的总天数
	 */
	@SuppressWarnings("deprecation")
	public static String getMonthLastDay(int month) {
		Date date = new Date();
		int[][] day = { { 0, 30, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 },
				{ 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 } };
		int year = date.getYear() + 1900;
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			return day[1][month] + "";
		} else {
			return day[0][month] + "";
		}
	}

	/**
	 * 返回指定年份中指定月份的天数
	 * 
	 * @param 年份year
	 * @param 月份month
	 * @return 指定月的总天数
	 */
	public static String getMonthLastDay(int year, int month) {
		int[][] day = { { 0, 30, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 },
				{ 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 } };
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			return day[1][month] + "";
		} else {
			return day[0][month] + "";
		}
	}

	/**
	 * 取得当前时间的日戳
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getTimestamp() {
		Date date = new Date();
		String timestamp = "" + (date.getYear() + 1900) + date.getMonth() + date.getDate() + date.getMinutes()
				+ date.getSeconds() + date.getTime();
		return timestamp;
	}

	/**
	 * 取得指定时间的日戳
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getTimestamp(Date date) {
		String timestamp = "" + (date.getYear() + 1900) + date.getMonth() + date.getDate() + date.getMinutes()
				+ date.getSeconds() + date.getTime();
		return timestamp;
	}

	public static Week getWeek(String sdate) {
		Date date = convertStringToDate(sdate);
		return getWeek(date);
	}

	/**
	 * @Title: getWeekOfYear
	 * @Description: 获取一年中的第几周
	 * @param: @param
	 *             date
	 * @param: @return
	 * @return: int
	 * @throws @since
	 *             JDK 1.7+
	 * @author 刘海林
	 */
	public static int getWeekOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * @Title: getWeekOfMonth
	 * @Description 获取一月中的第几周
	 * @param: @param
	 *             date
	 * @param: @return
	 * @return: int
	 * @throws @since
	 *             JDK 1.7+
	 * @author 刘海林
	 */
	public static int getWeekOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_MONTH);
	}

	/**
	 * 获取日期的星期。失败返回null。
	 * 
	 * @param date
	 *            日期
	 * @return 星期
	 */
	public static Week getWeek(Date date) {
		Week week = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int weekNumber = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		switch (weekNumber) {
		case 0:
			week = Week.SUNDAY;
			break;
		case 1:
			week = Week.MONDAY;
			break;
		case 2:
			week = Week.TUESDAY;
			break;
		case 3:
			week = Week.WEDNESDAY;
			break;
		case 4:
			week = Week.THURSDAY;
			break;
		case 5:
			week = Week.FRIDAY;
			break;
		case 6:
			week = Week.SATURDAY;
			break;
		}
		return week;
	}

}
