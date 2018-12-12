package com.liuyu.common.progress;

import java.util.List;

/**
 * ClassName: ProgressListener <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 17-11-27 上午11:26 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public interface ProgressListener {
    public void listener(List<ProgressEvent> events, boolean isOver);
}
