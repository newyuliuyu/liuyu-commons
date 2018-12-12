package com.liuyu.common.file.reader.spi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

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
public class SheetRowAndColumnHandler extends DefaultHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private int rowNum = 1;
    private int colNum = 1;

    private boolean sheetView = false;
    private boolean selection = false;

    public int getRowNum() {
        return rowNum;
    }

    public int getColNum() {
        return colNum;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("dimension".equals(qName)) {
            String ref = attributes.getValue("ref");
            parseRef(ref);
        } else if ("sheetView".equals(qName)) {
            sheetView = true;
        } else if (sheetView && "selection".equals(qName)) {
            selection = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("dimension".equals(qName)) {
            //throw new GetSheetRowAndColumnException("获取行数和列数");
        } else if ("sheetView".equals(qName)) {
            if (!selection) {
                colNum = 0;
                rowNum = 0;
            }
            selection = false;
            selection = false;
            throw new GetSheetRowAndColumnException("获取行数和列数");
        }
    }

    private void parseRef(String ref) {

        String[] values = ref.split(":");
        if (values.length == 1) {
            return;
        } else {
            colNum = calculateColumnNum(values[0], values[1]);
            rowNum = calculateRowNum(values[1]);
        }

        logger.debug("row:{},column:{}", rowNum, colNum);
    }

    private int calculateColumnNum(String colStr1, String colStr2) {
        String colChar1 = ParseExcelXmlHelper.findColChar(colStr1);
        String colChar2 = ParseExcelXmlHelper.findColChar(colStr2);
        int col1 = ParseExcelXmlHelper.colStrToInt(colChar1);
        int col2 = ParseExcelXmlHelper.colStrToInt(colChar2);
        int colNum = col2 - col1 + 1;
        return colNum;
    }

    private int calculateRowNum(String rowStr) {
        String rowChar = ParseExcelXmlHelper.findColNum(rowStr);
        return Integer.parseInt(rowChar);
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

    }
}
