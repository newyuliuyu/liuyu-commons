/**
 * Project Name:easydata.web
 * File Name:GradeNameOrderHelper.java
 * Package Name:com.ez.data.utils
 * Date:2017年4月19日上午10:44:45
 * Copyright (c) 2017, easytnt All Rights Reserved.
 */
package com.liuyu.common.util;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName: GradeNameOrderHelper <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年4月19日 上午10:44:45 <br/>
 *
 * @author 刘海林
 * @version v1.0
 * @since JDK 1.7+
 */
public class GradeNameOrderHelper {
    public static int transformationGradeNameToNum(String gradeName) {
        String grade1 = "";
        if (gradeName.contains("高")) {
            grade1 = "3";
        } else if (gradeName.contains("初")) {
            grade1 = "2";
        } else if (gradeName.contains("小")) {
            grade1 = "1";
        }
        Pattern pattern = Pattern.compile("[一二三四五六七八九十]+");
        Matcher matcher = pattern.matcher(gradeName);
        int grade2 = 0;
        if (matcher.find()) {
            String num = matcher.group();
            grade2 = NumberHelper.chineseToNumber(num);
        }
        return Integer.parseInt(grade1 + grade2);
    }

    public static GradeInfo getGradeInfo(String gradeName) {
        return getGradeInfo(gradeName, new Date());
    }

    public static GradeInfo getGradeInfo(String gradeName, Date date) {

        GradeInfo gradeInfo = new GradeInfo();

        int month = Integer.parseInt(Dateutil.getMonth(date));
        int year = Integer.parseInt(Dateutil.getYear(date));

        gradeInfo.setYear(year);
        gradeInfo.setMonth(month);

        if (month >= 2 || month < 9) {
            gradeInfo.setSemester(2);
        } else {
            gradeInfo.setSemester(1);
        }


        if (gradeName.equals("一年级")) {
            gradeInfo.setStudySetion(1);
        } else if (gradeName.equals("二年级")) {
            gradeInfo.setStudySetion(1);
            year = year - 1;
        } else if (gradeName.equals("三年级")) {
            gradeInfo.setStudySetion(1);
            year = year - 2;
        } else if (gradeName.equals("四年级")) {
            gradeInfo.setStudySetion(1);
            year = year - 3;
        } else if (gradeName.equals("五年级")) {
            gradeInfo.setStudySetion(1);
            year = year - 4;
        } else if (gradeName.equals("六年级")) {
            gradeInfo.setStudySetion(1);
            year = year - 5;
        } else if (gradeName.equals("七年级") || gradeName.equals("初一")) {
            gradeInfo.setStudySetion(2);
            year = year;
        } else if (gradeName.equals("八年级") || gradeName.equals("初二")) {
            gradeInfo.setStudySetion(2);
            year = year - 1;
        } else if (gradeName.equals("九年级") || gradeName.equals("初三")) {
            gradeInfo.setStudySetion(2);
            year = year - 2;
        } else if (gradeName.equals("高一")) {
            gradeInfo.setStudySetion(3);
        } else if (gradeName.equals("高二")) {
            gradeInfo.setStudySetion(2);
            year = year - 1;
        } else if (gradeName.equals("高三")) {
            gradeInfo.setStudySetion(2);
            year = year - 2;
        }
        if (gradeInfo.getSemester() == 1) {
            gradeInfo.setEntranceYear(year);
        } else {
            gradeInfo.setEntranceYear(year - 1);
        }

        if (gradeInfo.getStudySetion() == 1) {
            gradeInfo.setGraduationYear(gradeInfo.getEntranceYear() + 5);
        } else {
            gradeInfo.setGraduationYear(gradeInfo.getEntranceYear() + 2);
        }
        return gradeInfo;
    }

}
