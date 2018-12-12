/**
 * Project Name:easydata.etl
 * File Name:HeaderMetadata.java
 * Package Name:com.ez.etl
 * Date:2017年3月14日上午10:58:00
 * Copyright (c) 2017, easytnt All Rights Reserved.
 */
package com.liuyu.common.file.reader;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;
import java.util.Map;

/**
 * ClassName: HeaderMetadata <br/>
 * Function: 记录处理文件表头信息. <br/>
 * Reason: ADD REASON(可选). <br/>
 * date: 2017年3月14日 上午10:58:00 <br/>
 *
 * @author 刘海林
 * @version v1.0
 * @since JDK 1.7+
 */
public class HeaderMetadata {
    private int totalRow;
    private List<String> headerNames = Lists.newArrayList();
    //    private List<String> headerNames2 = Lists.newArrayList();
    private Map<String, Integer> headerNamesIndexMap = Maps.newHashMap();

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    public List<String> getHeaderNames() {
        return headerNames;
    }

    public void addHeaderName(String headerName, int idx) {
        String lowerHeaderName = headerName.trim().toLowerCase();
        if (headerNamesIndexMap.get(lowerHeaderName) == null) {
            headerNames.add(headerName);
            headerNamesIndexMap.put(lowerHeaderName, idx);
        }
    }

    public int getHeaderNameIndx(String headerName) {
        String lowerHeaderName = headerName.toLowerCase();
        Integer idx = headerNamesIndexMap.get(lowerHeaderName);
        idx = idx == null ? -1 : idx;
        return idx;
    }

    public HeaderMetadata append(String headerName, int idx) {
        addHeaderName(headerName, idx);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        for (String value : headerNames) {
            sb.append(value).append("[").append(idx++).append("]").append(",");
        }
        return new ToStringBuilder(this).append("rowNum", totalRow).append("headerNames", sb.toString()).build();
    }

}
