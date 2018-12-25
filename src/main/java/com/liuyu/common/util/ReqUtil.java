package com.liuyu.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName: ReqUtil <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-12-25 下午2:03 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class ReqUtil {
    public static boolean isAjax(HttpServletRequest request){
        //JQuery ajax
        if (request.getHeader("accept").indexOf("application/json") > -1)
            return Boolean.TRUE;

        //ajax文件上传
        if (request.getHeader("Content-Type") != null && request.getHeader("Content-Type").indexOf("multipart/form-data") > -1) {
            return Boolean.TRUE;
        }
        //移动端
        return (request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
    }
}
