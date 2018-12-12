/**
 * Project Name:easydata.etl
 * File Name:RowData.java
 * Package Name:com.ez.etl
 * Date:2017年3月14日上午11:02:00
 * Copyright (c) 2017, easytnt All Rights Reserved.
 */
package com.liuyu.common.file.reader;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;
import java.util.Map;

/**
 * ClassName: RowData <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年3月14日 上午11:02:00 <br/>
 *
 * @author 刘海林
 * @version v1.0
 * @since JDK 1.7+
 */
public class Rowdata {
    private int rowNum;
    private HeaderMetadata headerMetadata;
    private Map<Integer, Object> rowdata = Maps.newHashMap();

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public HeaderMetadata getHeaderMetadata() {
        return headerMetadata;
    }

    public void setHeaderMetadata(HeaderMetadata headerMetadata) {
        this.headerMetadata = headerMetadata;
    }


    public Rowdata addData(Object value, int idx) {
        rowdata.put(idx, value);
        return this;
    }

    public Rowdata addData(String headerName, Object value) {
        int idx = headerMetadata.getHeaderNameIndx(headerName);
        if (idx != -1) {
            addData(value, idx);
        }
        return this;
    }

    public <T> T getData(String headerName) {
        int idx = headerMetadata.getHeaderNameIndx(headerName);
        if (idx == -1) {
            return null;
        }
        return (T) rowdata.get(idx);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        List<String> names = headerMetadata.getHeaderNames();
        for (String name : names) {
            String value = getData(name);
            sb.append(value).append(",");
        }
        return new ToStringBuilder(this).append("rowIdx", rowNum).append("rowdata", sb.toString())
                .append("headers", headerMetadata.toString()).build();
    }

}
