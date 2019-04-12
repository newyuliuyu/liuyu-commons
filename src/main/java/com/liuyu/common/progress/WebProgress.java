package com.liuyu.common.progress;

import com.liuyu.common.cache.MemoryCache;
import com.liuyu.common.util.ThrowableToString;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;

/**
 * ClassName: WebProgress <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 19-1-7 下午5:32 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
@Slf4j
public class WebProgress {
    private int total;
    private String key;
    private boolean debug;
    private Progresses progresses;

    public WebProgress(String key, int total) {
        this(key, total, false);
    }

    public WebProgress(String key, int total, boolean debug) {
        this.total = total;
        this.key = key;
        this.debug = debug;
        initProgress();
    }

    private void initProgress() {
        if (debug) {
            return;
        }
        progresses = new Progresses(true);
        MemoryCache.instance().put(key, progresses);
    }

    public void changeTotal(int total) {
        this.total = total;
    }

    private boolean isDebug(ProgressEvent event) {
        if (debug) {
            log.debug(event.getMessage());
            return true;
        }
        return false;
    }

    public void start(String msg) {
        ProgressEvent event = new ProgressEvent(0, total, msg);
        if (!isDebug(event)) {
            progresses.publish(event);
        }
    }


    public void info(int completeNum, String message, Exception e) {
        info(completeNum, message + ThrowableToString.formatHtml(e));
    }

    public void info(int completeNum, String message) {
        ProgressEvent event = new ProgressEvent(changeComplateNum(completeNum), total, message);
        if (!isDebug(event)) {
            progresses.publish(event);
        }
    }

    public void info(int completeNum, String msgformat, Object... value) {
        String msg = MessageFormat.format(msgformat, value);
        info(completeNum, msg);
    }

    public void warn(int completeNum, String message, Exception e) {
        warn(completeNum, message + ThrowableToString.formatHtml(e));
    }

    public void warn(int completeNum, String message) {
        ProgressEvent event = new ProgressEvent(changeComplateNum(completeNum), total, message);
        event.setMsgType(2);
        if (!isDebug(event)) {
            progresses.publish(event);
        }
    }

    public void error(int completeNum, String message, Exception e) {
        error(completeNum, message + ThrowableToString.formatHtml(e));
    }

    public void error(int completeNum, String message) {
        ProgressEvent event = new ProgressEvent(changeComplateNum(completeNum), total, message);
        event.setMsgType(1);
        if (!isDebug(event)) {
            progresses.publish(event);
        }
    }

    private int changeComplateNum(int completeNum) {
        return completeNum == total ? total - 1 : completeNum;
    }

    public void over(String message) {
        ProgressEvent event = new ProgressEvent(total, total, message);
        event.setOver(true);
        if (!isDebug(event)) {
            progresses.publish(event);
        }
    }
}
