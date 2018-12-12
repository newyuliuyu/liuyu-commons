package com.liuyu.common.file.reader.spi;

import org.apache.poi.xssf.model.SharedStringsTable;

/**
 * ClassName: SheetNameHandler <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 18-3-8 下午6:25 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class SheetHeaderHandler extends AbstractSheetDataHandler {


    public SheetHeaderHandler(SharedStringsTable sst) {
        this(sst, 1);
    }

    public SheetHeaderHandler(SharedStringsTable sst, int rowIdx) {
        super(sst, rowIdx);
    }

    public Excel2007Cell[] getData() {
        return data.toArray(new Excel2007Cell[0]);
    }

    @Override
    protected boolean isContinue() {
        return curRowIdx == rowIdx;
    }

    @Override
    protected void processEndRow() {
        throw new GetSheetHeaderException("获取表头数据完毕");
    }
}
