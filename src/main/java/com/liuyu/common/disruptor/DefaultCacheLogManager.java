package com.liuyu.common.disruptor;


import com.liuyu.common.cache.MemoryCache;

import java.text.MessageFormat;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName: DefaultLogManager <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-11-2 下午3:40 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class DefaultCacheLogManager implements CacheLogManager {
    private String key;
    private TaskProcess taskProcess;
    private Lock lock = new ReentrantLock();

    public DefaultCacheLogManager(String key) {
        this.key = key;
        restCache(100, "");
    }

    private void increase() {
        lock.lock();
        try {
            taskProcess.setCompleted(taskProcess.getCompleted() + 1);
        } finally {
            lock.unlock();
        }
    }

    private void increase(int complate) {
        lock.lock();
        try {
            taskProcess.setCompleted(complate);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void setFinished(String message) {
        taskProcess.setTotal(100);
        taskProcess.setCompleted(100);
        taskProcess.setFinished(true);
        taskProcess.setText(message);
    }

    @Override
    public void restCache(int totalNum, String message) {
        taskProcess = new TaskProcess(totalNum, message);
        MemoryCache.instance().put(key, taskProcess);
    }

    @Override
    public void increaseLog(String message) {
        increase();
        log(message);
    }

    @Override
    public void increaseLog(String messageFormat, Object... args) {
        increase();
        log(messageFormat, args);
    }

    @Override
    public void log(int complate, String message) {
        increase(complate);
        log(message);
    }

    @Override
    public void log(int complate, String messageFormat, Object... args) {
        increase(complate);
        log(messageFormat, args);
    }

    @Override
    public void log(String message) {
        taskProcess.setText(message);
    }

    @Override
    public void log(String messageFomart, Object... args) {
        String message = MessageFormat.format(messageFomart, args);
        this.log(message);
    }

    @Override
    public void error(String message) {
        taskProcess.addErrorMessage(message);
    }

    @Override
    public void warn(String message) {
        taskProcess.addWarnMessage(message);
    }
}
