/**
 * Project Name:easytnt-ez
 * File Name:ExcelTable.java
 * Package Name:com.ez.framwork.report.excle.util
 * Date:2017年3月7日上午10:20:01
 * Copyright (c) 2017, easytnt All Rights Reserved.
 */
package com.liuyu.common.excel;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * ClassName: ExcelTable <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年3月7日 上午10:20:01 <br/>
 *
 * @author 刘海林
 * @version v1.0
 * @since JDK 1.7+
 */
public class ExcelTable {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    protected static DecimalFormat decimalFormat = new DecimalFormat("#.######");
    protected static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private Workbook wb;
    private CellStyle cs;
    private Sheet sheet;
    private Row row;

    public ExcelTable() {
        wb = new HSSFWorkbook();
        setCellStyle();
    }

    public Row getRow() {
        return row;
    }

    private void setCellStyle() {
        if (cs == null) {
            cs = wb.createCellStyle();
            cs.setAlignment(HorizontalAlignment.CENTER);
            cs.setVerticalAlignment(VerticalAlignment.CENTER);

            Font font = wb.createFont();
            font.setFontName("宋体");
            font.setItalic(false);
            font.setBold(false);
            font.setColor(Font.COLOR_NORMAL);
            font.setFontHeightInPoints((short) 9);
            font.setStrikeout(false);
            cs.setFont(font);

            cs.setBorderTop(BorderStyle.THIN);
            cs.setBorderRight(BorderStyle.THIN);
            cs.setBorderBottom(BorderStyle.THIN);
            cs.setBorderLeft(BorderStyle.THIN);
        }
    }

    public ExcelTable resetRow(int rowIdx) {
        row = sheet.getRow(rowIdx);
        if (row == null) {
            createRow(rowIdx);
        }
        return this;
    }

    public void autoSizeColumn(int size) {
        for (int i = 0; i < size; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    public CellStyle getCellStyle() {
        if (cs == null) {
            setCellStyle();
        }
        return cs;
    }

    public CellStyle newStyle() {
        CellStyle cs = wb.createCellStyle();
        cs.setAlignment(HorizontalAlignment.CENTER);
        cs.setVerticalAlignment(VerticalAlignment.CENTER);

        Font font = wb.createFont();
        font.setFontName("宋体");
        font.setItalic(false);
        font.setBold(false);
        font.setColor(Font.COLOR_NORMAL);
        font.setFontHeightInPoints((short) 9);
        font.setStrikeout(false);
        cs.setFont(font);

        cs.setBorderTop(BorderStyle.THIN);
        cs.setBorderRight(BorderStyle.THIN);
        cs.setBorderBottom(BorderStyle.THIN);
        cs.setBorderLeft(BorderStyle.THIN);

        return cs;
    }

    public CellStyle getDesStyle() {
        CellStyle cs = wb.createCellStyle();
        cs.setAlignment(HorizontalAlignment.LEFT);
        cs.setVerticalAlignment(VerticalAlignment.CENTER);

        Font font = wb.createFont();
        font.setFontName("宋体");
        font.setItalic(false);
        font.setBold(false);
        font.setColor(Font.COLOR_NORMAL);
        font.setFontHeightInPoints((short) 9);
        font.setStrikeout(false);
        cs.setFont(font);

        cs.setBorderTop(BorderStyle.THIN);
        cs.setBorderRight(BorderStyle.THIN);
        cs.setBorderBottom(BorderStyle.THIN);
        cs.setBorderLeft(BorderStyle.THIN);

        return cs;
    }

    public CellStyle getHeaderStyle() {
        CellStyle cs = wb.createCellStyle();
        cs.setAlignment(HorizontalAlignment.CENTER);
        cs.setVerticalAlignment(VerticalAlignment.CENTER);

        Font font = wb.createFont();
        font.setFontName("宋体");
        font.setItalic(false);
        font.setBold(true);
        font.setColor(Font.COLOR_NORMAL);
        font.setFontHeightInPoints((short) 9);
        font.setStrikeout(false);
        cs.setFont(font);

        cs.setBorderTop(BorderStyle.THIN);
        cs.setBorderRight(BorderStyle.THIN);
        cs.setBorderBottom(BorderStyle.THIN);
        cs.setBorderLeft(BorderStyle.THIN);

        cs.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);// 设置背景色
        cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return cs;
    }

    public ExcelTable addSheet(String sheetName) {
        Preconditions.checkNotNull(wb, "工作簿不能为空");
        Preconditions.checkNotNull(sheetName, "工作簿名称不能为空");
        sheet = wb.createSheet(sheetName);
        return this;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public ExcelTable changeSheet(String sheetName) {
        Preconditions.checkNotNull(wb, "工作簿不能为空");
        Preconditions.checkNotNull(sheetName, "工作簿名称不能为空");
        sheet = wb.getSheet(sheetName);
        if (sheet == null) {
            addSheet(sheetName);
        }
        return this;
    }

    public ExcelTable createRow(int rowIdx) {
        Preconditions.checkNotNull(sheet, "工作页面不能为空");
        Preconditions.checkArgument(rowIdx >= 0, "行号必须大于0");
        row = sheet.createRow(rowIdx);
        row.setHeightInPoints(12.75f);
        return this;
    }

    public ExcelTable createRowAndCells(int rowIdx, Object... texts) {
        createRowAndCells(rowIdx, 0, null, new ExcelRowdata(texts));
        return this;
    }

    public ExcelTable createRowAndCells(int rowIdx, CellStyle style, Object... texts) {
        createRowAndCells(rowIdx, 0, style, new ExcelRowdata(texts));
        return this;
    }

    public ExcelTable createRowAndCells(int rowIdx, List<? extends Object> texts) {
        createRowAndCells(rowIdx, 0, null, new ExcelRowdata(texts));
        return this;
    }

    public ExcelTable createRowAndCells(int rowIdx, CellStyle style, List<? extends Object> texts) {
        createRowAndCells(rowIdx, 0, style, new ExcelRowdata(texts));
        return this;
    }

    public ExcelTable createRowAndCells(int rowIdx, ExcelRowdata texts) {
        createRowAndCells(rowIdx, 0, null, texts);
        return this;
    }

    public ExcelTable createRowAndCells(int rowIdx, CellStyle style, ExcelRowdata texts) {
        createRowAndCells(rowIdx, 0, style, texts);
        return this;
    }

    private ExcelTable createRowAndCells(int rowIdx, int beginColumn, CellStyle style, ExcelRowdata texts) {
        createRow(rowIdx);
        int size = texts.size();
        for (int i = 0, colIdx = beginColumn; i < size; i++) {
            createCell(colIdx++, texts.get(i), style);
        }
        return this;
    }

    public ExcelTable tableDescribe(int rowIdx, String text, int mergeColumnNum) {
        CellStyle desStyle = getDesStyle();
        createRowAndCells(rowIdx, desStyle, text);
        getRow().setHeightInPoints(100);
        if (mergeColumnNum != 0) {
            mergeCells(rowIdx, rowIdx, 0, mergeColumnNum);
        }
        return this;
    }

    private void createCell(int column, Object text, CellStyle style) {
        Preconditions.checkNotNull(wb, "工作簿不能为空");
        Preconditions.checkNotNull(row, "工作行不能为空");
        Preconditions.checkArgument(column >= 0, "列号必须大于0");
        Cell cell = row.createCell(column);
        setCellValue(cell, text);
        CellStyle cs = style;
        if (style == null) {
            cs = getCellStyle();
        }
        cell.setCellStyle(cs);
    }

    public ExcelTable appendCells(int beginColumns, Object... texts) {
        appendCells(beginColumns, null, new ExcelRowdata(texts));
        return this;
    }

    public ExcelTable appendCells(int beginColumns, CellStyle style, Object... texts) {
        appendCells(beginColumns, style, new ExcelRowdata(texts));
        return this;
    }

    public ExcelTable appendCells(int beginColumns, ExcelRowdata rowdata) {
        appendCells(beginColumns, null, rowdata);
        return this;
    }

    public ExcelTable appendCells(int beginColumns, CellStyle style, ExcelRowdata rowdata) {
        Preconditions.checkNotNull(row, "工作行不能为空");
        Preconditions.checkNotNull(rowdata, "行不能为空");
        for (int i = beginColumns, idx = 0, size = rowdata.size() + beginColumns; i < size; i++) {
            createCell(i, rowdata.get(idx++), style);
        }
        return this;
    }

    private void setCellValue(Cell cell, Object text) {
        Preconditions.checkNotNull(cell, "单元格不能为空");

        if (null == text) {
            text = "";
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (text instanceof Date) {
            cell.setCellValue(df.format((Date) text));
        } else if (text instanceof Boolean) {
            cell.setCellValue((Boolean) text);
        } else if (text instanceof Calendar) {
            Calendar c = (Calendar) text;
            String str = getDateTime(c);
            cell.setCellValue(str);
        } else if (text instanceof Double) {
            cell.setCellValue((Double) text);
        } else if (text instanceof ExcelFun) {
            cell.setCellFormula(text.toString());
        } else {
            String str = text.toString();
            if (str.length() < 10 && isNumber(str)) {
                cell.setCellValue(Double.parseDouble(str));
            } else {
                cell.setCellValue(str);
            }
        }
    }

    private String getDateTime(Calendar c) {
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;// 月
        int day = c.get(Calendar.DATE); // 日

        int hours = c.get(Calendar.HOUR);
        int minutes = c.get(Calendar.MINUTE);
        int seconds = c.get(Calendar.SECOND);

        String str = new StringBuilder().append(year).append("-").append(formatTime(month)).append("-")
                .append(formatTime(day)).append(" ").append(formatTime(hours)).append(":").append(formatTime(minutes))
                .append(":").append(formatTime(seconds)).toString();
        return str;
    }

    private String formatTime(int time) {
        String str = time + "";
        if (time < 10) {
            str = "0" + str;
        }

        return str;
    }

    private boolean isNumber(String text) {
        if (StringUtils.isBlank(text)) {
            return false;
        }

        boolean isNumber = true;
        try {
            Double.parseDouble(text);
        } catch (NumberFormatException e) {
            isNumber = false;
        }

        return isNumber;
    }

    public ExcelTable mergeCells(int firstRowIdx, int lastRowIdx, int firstColIdx, int lastColIdx) {
        sheet.addMergedRegion(new CellRangeAddress(firstRowIdx, lastRowIdx, firstColIdx, lastColIdx));
        return this;
    }

    public ExcelTable createDropDownMenu(int firstRowIdx, int lastRowIdx, int firstColIdx, int lastColIdx, String[] data) {
        DataValidationHelper helper = sheet.getDataValidationHelper();
        DataValidationConstraint constraint = helper.createExplicitListConstraint(data);
        CellRangeAddressList addressList = new CellRangeAddressList(firstRowIdx, lastRowIdx, firstColIdx, lastColIdx);
        DataValidation dataValidation = helper.createValidation(constraint, addressList);
        //处理Excel兼容性问题
        if (dataValidation instanceof XSSFDataValidation) {
            dataValidation.setSuppressDropDownArrow(true);
            dataValidation.setShowErrorBox(true);
        } else {
            dataValidation.setSuppressDropDownArrow(false);
        }
        sheet.addValidationData(dataValidation);
        return this;
    }

    public void save(String path) throws IOException {
        Preconditions.checkNotNull(wb, "工作簿不能为空");
        Preconditions.checkNotNull(path, "Excel生成路径不能为空");
        notExistsParentDirAndMkdirs(path);
        logger.debug("保存报表:{}", path);
        FileOutputStream output = new FileOutputStream(path);
        wb.write(output);
        wb.close();
        output.close();
    }

    public void save(OutputStream out) throws IOException {
        wb.write(out);
        wb.close();
    }

    private void notExistsParentDirAndMkdirs(String filePath) {
        Path path = Paths.get(filePath);
        File file = path.getParent().toFile();
        if (!file.exists()) {
            file.mkdirs();
        }
    }

}
