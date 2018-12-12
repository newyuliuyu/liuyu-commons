package com.liuyu.common.disruptor;

/**
 * ClassName: LogManager <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-11-2 下午3:38 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public interface LogManager {

    void log(String message);

    void error(String message);

    void warn(String message);

    /**
     * @param messageFomart 格式为数据{0}数据{1}
     * @param args
     */
    void log(String messageFomart, Object... args);
}
