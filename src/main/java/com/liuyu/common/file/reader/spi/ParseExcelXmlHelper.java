package com.liuyu.common.file.reader.spi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName: ParseExcelXmlHelper <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 18-3-9 下午3:09 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class ParseExcelXmlHelper {
    private static final Pattern patternChar = Pattern.compile("([A-Z]+)");
    private static final Pattern patternNum = Pattern.compile("([0-9]+)");

    public static int colStrToInt(String colStr) {
        final int unit = 26;
        char[] chars = colStr.toCharArray();
        int size = chars.length;
        int sum = 0;
        for (int i = size - 1; i >= 0; i--) {
            int toValue = (int) ((chars[i] - 64) * Math.pow(unit, size - i - 1));
            sum += toValue;
        }
        return sum;
    }
    public static String findColChar(String colStr) {
        Matcher matcher = patternChar.matcher(colStr);
        matcher.find();
        String colChar = matcher.group();
        return colChar;
    }
    public static String findColNum(String colStr) {
        Matcher matcher = patternNum.matcher(colStr);
        matcher.find();
        String colNum = matcher.group();
        return colNum;
    }
}
