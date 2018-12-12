package com.liuyu.common.progress;

import java.util.function.Supplier;

/**
 * ClassName: ProgressEvent <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 17-11-27 上午11:27 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class ProgressEvent {
    private int msgType = 3;//1 错误 2 警告 3 消息
    private long id;
    private String message;
    private long timestamp;
    private long time;
    private boolean over = false;
    private int totalNum;
    private int completeNum;
    private int percent;

    public static ProgressEvent create(Supplier<ProgressEvent> supplier) {
        return supplier.get();
    }

    public ProgressEvent() {

    }

    public ProgressEvent(int completeNum, int totalNum, String message) {
        this(completeNum, totalNum, message, false);
    }

    public ProgressEvent(int completeNum, int totalNum, String message, boolean isOver) {
        this.totalNum = totalNum;
        this.completeNum = completeNum;
        Double percent = completeNum * 1.0 / totalNum * 100;
        this.percent = percent.intValue();
        this.message = message;
        this.over = isOver;
    }
    public ProgressEvent message(int completeNum, int totalNum, String message) {
       return message(completeNum, totalNum, message, false);
    }

    public ProgressEvent message(int completeNum, int totalNum, String message, boolean isOver) {
        this.totalNum = totalNum;
        this.completeNum = completeNum;
        Double percent = completeNum * 1.0 / totalNum * 100;
        this.percent = percent.intValue();
        this.message = message;
        this.over = isOver;
        return this;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getCompleteNum() {
        return completeNum;
    }

    public void setCompleteNum(int completeNum) {
        this.completeNum = completeNum;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
