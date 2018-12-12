/**
 * Project Name:easydata.etl
 * File Name:XlsReader.java
 * Package Name:com.ez.etl.file.impl
 * Date:2017年3月14日下午1:46:20
 * Copyright (c) 2017, easytnt All Rights Reserved.
 */
package com.liuyu.common.file.reader.spi;

import com.google.common.base.Throwables;
import com.liuyu.common.file.reader.FileProcess;
import com.liuyu.common.file.reader.HeaderMetadata;
import com.liuyu.common.file.reader.Rowdata;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ClassName: XlsReader <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年3月14日 下午1:46:20 <br/>
 *
 * @author 刘海林
 * @version v1.0
 * @since JDK 1.7+
 */
public class ExcelReader implements FileProcess {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static DecimalFormat decimalFormat = new DecimalFormat("#.######");
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Path filepath;
    private Workbook wb;
    private Sheet sheet;
    private HeaderMetadata headerMetadata = new HeaderMetadata();
    private int curRowIdx = 1;

    public ExcelReader(Path filepath) {
        this.filepath = filepath;
        open();
    }

    private void open() {
        try {
            wb = WorkbookFactory.create(filepath.toFile());
            sheet = wb.getSheetAt(0);
            int rowNum = sheet.getPhysicalNumberOfRows();
            headerMetadata.setTotalRow(rowNum);

            int firstRow = curRowIdx = sheet.getFirstRowNum();
            Row row = sheet.getRow(firstRow);
            int idx = 0;
            for (Cell cell : row) {
                String value = parse(cell);
                headerMetadata.addHeaderName(value, idx++);
            }
            curRowIdx += 1;
        } catch (Exception e) {
            Throwables.propagate(new RuntimeException(String.format("打开xls文件%s出错", filepath), e));
        }
    }

    @Override
    public HeaderMetadata getHeaderMetadata() {
        return headerMetadata;
    }

    @Override
    public boolean close() {
        try {
            if (wb != null) {
                wb.close();
            }
        } catch (Exception e) {
            Throwables.propagate(new RuntimeException(String.format("关闭xls文件%s出错", filepath), e));
        }
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.ez.etl.Process#process(com.ez.etl.Rowdata)
     */
    @Override
    public Rowdata process(Rowdata rowdata) {

        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.ez.etl.file.FileProcess#next()
     */
    @Override
    public boolean next() {
        return curRowIdx < headerMetadata.getTotalRow();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.ez.etl.file.FileProcess#getRowdata()
     */
    @Override
    public Rowdata getRowdata() {
        Rowdata rowdata = new Rowdata();
        rowdata.setHeaderMetadata(headerMetadata);
        rowdata.setRowNum(curRowIdx);

        Row row = sheet.getRow(curRowIdx);
        int size = headerMetadata.getHeaderNames().size();
        for (int i = 0; i < size; i++) {
            Cell cell = row.getCell(i);
            String value = parse(cell);
            rowdata.addData(value, i);
        }
        curRowIdx++;
        return rowdata;
    }

    private String parse(Cell cell) {
        if (cell == null)
            return "";
        String result = new String();
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:// 数字类型
                if (DateUtil.isCellDateFormatted(cell)) {// 处理日期格式、 时间格式
                    Date date = cell.getDateCellValue();
                    result = dateFormat.format(date);
                } else {
                    double va = cell.getNumericCellValue();
                    if (va == (int) va)// 去掉数值类型后面的".0"
                        result = String.valueOf((int) va);
                    else
                        // result = String.valueOf(va); //if the double value is too
                        // big, it will be displayed in E-notation
                        result = decimalFormat.format(va);
                }
                break;
            case Cell.CELL_TYPE_FORMULA:
                // cell.getCellFormula();
                try {
                    result = String.valueOf(cell.getNumericCellValue());
                } catch (IllegalStateException e) {
                    result = String.valueOf(cell.getRichStringCellValue()).trim();
                }
                break;
            case Cell.CELL_TYPE_STRING:// String类型
                result = cell.getRichStringCellValue().toString().trim();
                break;
            case Cell.CELL_TYPE_BLANK:
                result = "";
                break;
            default:
                result = "";
                break;
        }
        return result.trim();
    }

}
