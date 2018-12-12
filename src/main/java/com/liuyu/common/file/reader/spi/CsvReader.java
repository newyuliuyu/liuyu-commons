/**
 * Project Name:easydata.etl
 * File Name:DBFReader.java
 * Package Name:com.ez.etl.file.impl
 * Date:2017年3月14日下午6:27:55
 * Copyright (c) 2017, easytnt All Rights Reserved.
 */
package com.liuyu.common.file.reader.spi;

import com.google.common.base.Throwables;
import com.liuyu.common.file.reader.FileProcess;
import com.liuyu.common.file.reader.HeaderMetadata;
import com.liuyu.common.file.reader.Rowdata;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.List;

/**
 * ClassName: DBFReader <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2017年3月14日 下午6:27:55 <br/>
 *
 * @author 刘海林
 * @version v1.0
 * @since JDK 1.7+
 */
public class CsvReader implements FileProcess {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Path filepath;
    private HeaderMetadata headerMetadata = new HeaderMetadata();
    private List<String> lines;
    private int curRowIdx = 1;

    public CsvReader(Path filepath) {
        this(filepath, "utf-8");
    }

    public CsvReader(Path filepath, String encoding) {
        this.filepath = filepath;
        init(encoding);
    }

    private void init(String encoding) {
        try {
            lines = FileUtils.readLines(filepath.toFile(), encoding);
            if (lines.isEmpty()) {
                Throwables.propagate(new RuntimeException(String.format("csv文件%s没有数据", filepath)));
            }
            headerMetadata.setTotalRow(lines.size());
            String[] fileds = lines.get(0).split(",");
            // 取出字段信息
            int idx = 0;
            for (String filed : fileds) {
                headerMetadata.addHeaderName(filed.trim(), idx++);
            }
        } catch (Exception e) {
            Throwables.propagate(new RuntimeException(String.format("打开csv文件%s出错", filepath), e));
        }
    }

    @Override
    public Rowdata process(Rowdata rowdata) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public HeaderMetadata getHeaderMetadata() {
        return headerMetadata;
    }

    @Override
    public boolean next() {
        return curRowIdx < headerMetadata.getTotalRow();
    }

    @Override
    public Rowdata getRowdata() {
        Rowdata rowdata = new Rowdata();
        rowdata.setRowNum(curRowIdx);
        rowdata.setHeaderMetadata(headerMetadata);
        String[] fileds = lines.get(curRowIdx).split(",");
        int idx = 0;
        for (String filed : fileds) {
            rowdata.addData(filed.trim(), idx++);
        }
        curRowIdx++;
        return rowdata;
    }

    @Override
    public boolean close() {
        return true;
    }

}
