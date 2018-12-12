package com.liuyu.common.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName: HttpReqUtils <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-10-23 下午4:37 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class HttpReqUtils {
    public static String getParamString(HttpServletRequest req, String paramName) {
        return req.getParameter(paramName);
    }

    public static int getParamInt(HttpServletRequest req, String paramName) {
        String value = req.getParameter(paramName);
        if (StringUtils.isEmpty(value)) {
            value = "0";
        }
        return Integer.parseInt(value);
    }

    public static long getParamLong(HttpServletRequest req, String paramName) {
        String value = req.getParameter(paramName);
        if (StringUtils.isEmpty(value)) {
            value = "0";
        }
        return Long.parseLong(value);
    }

    public static boolean getParamBoolean(HttpServletRequest req, String paramName) {
        String value = req.getParameter(paramName);
        if (StringUtils.isEmpty(value)) {
            value = "false";
        }
        return Boolean.parseBoolean(value);
    }

    public static String[] getParamArray(HttpServletRequest req, String paramName) {
        String[] values = req.getParameterValues(paramName);

        return values;
    }
}
