package com.liuyu.common.progress;

import com.google.common.collect.Lists;
import com.liuyu.common.cache.MemoryCache;
import com.liuyu.common.util.IdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ClassName: Progresses <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 17-11-27 上午11:28 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class Progresses {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private IdGenerator idGenerator = new IdGenerator();
    private CopyOnWriteArrayList<ProgressEvent> msgs = new CopyOnWriteArrayList<>();
    private ConcurrentLinkedQueue<ProgressListener> listeners = new ConcurrentLinkedQueue<>();
    private AtomicLong timestamp = new AtomicLong(0L);
    private AtomicBoolean over = new AtomicBoolean(false);
    private AtomicBoolean erro = new AtomicBoolean(false);
    private boolean isSaveMsg = false;

    public Progresses() {
        this(false);
    }

    public Progresses(boolean isSaveMsg) {
        this.isSaveMsg = isSaveMsg;
    }

    public void register(ProgressListener listener, long timestamp) {
        if (timestamp - this.timestamp.get() < 0 || (timestamp - this.timestamp.get() <= 0 && over.get())) {
            processEvent(listener, timestamp);
        } else {
            listeners.add(listener);
        }
    }

    private void processEvent(ProgressListener listener, long timestamp) {
        List<ProgressEvent> msgs = Lists.newArrayList(this.msgs);


        if (!isSaveMsg) {
            this.msgs.clear();
        }


        List<ProgressEvent> result = Lists.newArrayList();
        for (ProgressEvent msg : msgs) {
            if (msg.getTimestamp() - timestamp >= 0) {
                result.add(msg);
            }
        }
        listener.listener(result, over.get());
    }

    public void publish(ProgressEvent event) {
        long timestamp = System.currentTimeMillis();
        long id = idGenerator.nextId();
        event.setId(id);
        event.setTimestamp(id);
        event.setTime(timestamp);

        if (event.getMsgType() == 1) {
            erro.set(true);
        }
        this.over.compareAndSet(false, event.isOver());

        this.timestamp.set(id);


        if (isSaveMsg || listeners.isEmpty()) {
            msgs.add(event);
        }

        List<ProgressEvent> events = Lists.newArrayList(event);
        ProgressListener listener = listeners.poll();
        while (listener != null) {
            listener.listener(events, over.get());
            listener = listeners.poll();
        }
    }

    public String saveToCache() {

        return saveToCache("");
    }

    public String saveToCache(String prefix) {
        String key = "" + idGenerator.nextId();

        if (!StringUtils.isEmpty(prefix)) {
            key = prefix + "-" + key;
        }
        return saveToCacheWithCustomKey(key);
    }

    public String saveToCacheWithCustomKey(String customKey) {
        MemoryCache.instance().put(customKey, this);
        return customKey;
    }

    public static Progresses getFromCache(String key) {
        return MemoryCache.instance().getValue(key);
    }

    public void print() {
        logger.debug("可发送消息数量：{}", msgs.size());
        logger.debug("监听任务数量：{}", listeners.size());
        logger.debug("是否有错误：{}", erro.get());
        logger.debug("是否完成：{}", over.get());
        logger.debug("是否一直保存消息到最后：{}", isSaveMsg);
    }
}
