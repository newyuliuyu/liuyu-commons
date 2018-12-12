/**
 * Project Name:easytnt-ez
 * File Name:Rowdata.java
 * Package Name:com.ez.framwork.report.excle.util
 * Date:2017年3月7日下午1:37:49
 * Copyright (c) 2017, easytnt All Rights Reserved.
 */
package com.liuyu.common.excel;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * ClassName: Rowdata <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年3月7日 下午1:37:49 <br/>
 *
 * @author 刘海林
 * @version v1.0
 * @since JDK 1.7+
 */
public class ExcelRowdata {
    private List<Object> rowdata = Lists.newArrayList();

    public ExcelRowdata() {
    }


    public ExcelRowdata(int capacity) {
        rowdata = Lists.newArrayListWithCapacity(capacity);
        for (int i = 0; i < capacity; i++) {
            rowdata.add(null);
        }
    }

    public ExcelRowdata(Object... objects) {
        for (Object obj : objects) {
            rowdata.add(obj);
        }
    }

    public ExcelRowdata(List<? extends Object> objects) {
        rowdata.addAll(objects);
    }

    public ExcelRowdata append(Object... objs) {
        for (Object obj : objs) {
            rowdata.add(obj);
        }
        return this;
    }

    public ExcelRowdata set(int idx, Object... objs) {
        for (Object obj : objs) {
            rowdata.set(idx++, obj);
        }
        return this;
    }

    public int size() {
        return rowdata.size();
    }

    public Object get(int idx) {
        return rowdata.get(idx);
    }

    public Object[] toArray() {
        return rowdata.toArray(new Object[0]);
    }
}
