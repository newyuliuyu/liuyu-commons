package com.liuyu.common.disruptor;

/**
 * ClassName: CacheLogManager <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-11-2 下午3:43 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public interface CacheLogManager extends LogManager {
    void restCache(int totalNum, String message);

    void setFinished(String message);

    void increaseLog(String message);

    void increaseLog(String messageFormat, Object... args);

    void log(int complate, String message);

    void log(int complate, String messageFormat, Object... args);

}
