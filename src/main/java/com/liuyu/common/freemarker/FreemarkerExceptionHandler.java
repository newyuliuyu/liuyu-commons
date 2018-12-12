package com.liuyu.common.freemarker;


import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Writer;

/**
 * ClassName: FreemarkerExceptionHandler <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 17-11-9 下午4:42 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class FreemarkerExceptionHandler implements TemplateExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void handleTemplateException(TemplateException te, Environment env, Writer out) throws TemplateException {

        if (true) {
            throw new RuntimeException("解析freemarker失败", new TemplateException(
                    "Failed to print error message. Cause: " + te, env));
        }
//        throw new EasyException(ExCode.OTHER,"测试问题",te);
        logger.warn("[Freemarker Error: " + te.getMessage() + "]");
        String missingVariable = "undefined";
        try {
            String[] tmp = te.getMessageWithoutStackTop().split("\n");
            if (tmp.length > 1)
                tmp = tmp[1].split(" ");
            if (tmp.length > 1)
                missingVariable = tmp[1];

            out.write("[出错了，请联系网站管理员：${ " + missingVariable
                    + "}]");
            logger.error("[出错了，请联系网站管理员]", te);
        } catch (IOException e) {
            throw new RuntimeException("解析freemarker失败", new TemplateException(
                    "Failed to print error message. Cause: " + e, env));
        }
    }
}
