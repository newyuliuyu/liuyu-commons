package com.liuyu.common.progress;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * ClassName: DefaultProcessListener <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 17-11-27 下午1:29 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class DefaultProgressListener implements ProgressListener {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private List<ProgressEvent> events = Lists.newArrayList();

    private boolean over = false;

    public synchronized List<ProgressEvent> getEvents() {
        if (events.isEmpty()) {
            try {
                this.wait(60000L);
            } catch (Exception e) {
                logger.error("等待资源超时错误", e);
            }
        }
        return events;
    }

    public boolean isOver() {
        return over;
    }


    @Override
    public synchronized void listener(List<ProgressEvent> events, boolean isOver) {
//        int size = events.size();
//        for (int i = size - 1; i >= 0; i--) {
//            ProgressEvent event = events.get(i);
//            if (event.isOver()) {
//                over = true;
//                break;
//            }
//        }
        over = isOver;
        doEvent(events);
        this.notify();
    }

    protected void doEvent(List<ProgressEvent> events) {
        this.events.addAll(events);
    }
}
