package com.liuyu.common.file.reader.spi;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * ClassName: Excel2007Cell <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-3-16 下午12:56 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class Excel2007Cell {
    private String value;
    private int colIdx;

    public String getValue() {
        return value.trim();
    }

    public Excel2007Cell setValue(String value) {
        this.value = value;
        return this;
    }

    public int getColIdx() {
        return colIdx;
    }

    public Excel2007Cell setColIdx(int colIdx) {
        this.colIdx = colIdx;
        return this;

    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("value", value)
                .append("colIdx", colIdx)
                .toString();
    }
}
