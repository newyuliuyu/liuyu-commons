package com.liuyu.common.file.reader.spi;

import com.google.common.collect.Lists;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.List;

/**
 * ClassName: AbstractSheetDataHandler <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 18-3-9 下午5:56 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class AbstractSheetDataHandler extends DefaultHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    protected SharedStringsTable sst;
    protected List<Excel2007Cell> data;
    protected int rowIdx = 0;
    protected int colNum = 1;
    protected int rowNum = 1;

    protected int curColIdx = 0;
    protected int curRowIdx = 0;
    protected boolean nextIsString = false;
    protected StringBuilder content = new StringBuilder();

    public AbstractSheetDataHandler(SharedStringsTable sst) {
        this(sst, 1);
    }

    public AbstractSheetDataHandler(SharedStringsTable sst, int rowIdx) {
        this.sst = sst;
        this.rowIdx = rowIdx;
    }

    private void initRow() {
        data = null;
        rowIdx = 0;
        curRowIdx = 0;
    }

    private void initCell() {
        curColIdx = 0;
        nextIsString = false;
        content = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("dimension".equals(qName)) {
            String ref = attributes.getValue("ref");
            parseRef(ref);
        } else if ("row".equals(qName)) {
            initRow();
            parseStartRow(qName, attributes);
        }

        if (!isContinue()) {
            return;
        }
        if ("c".equals(qName)) {
            initCell();
            parseStartC(qName, attributes);

        } else if ("v".equals(qName)) {
            parseStartV(qName, attributes);
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

    private int calculateBegin(String rowStr) {
        String rowChar = ParseExcelXmlHelper.findColNum(rowStr);
        return Integer.parseInt(rowChar);
    }

    protected void parseStartRow(String qName, Attributes attributes) {
        data = Lists.newArrayList();
        curRowIdx = Integer.parseInt(attributes.getValue("r"));
    }

    protected void parseStartC(String qName, Attributes attributes) {
        String r = attributes.getValue("r");
        String colChar = ParseExcelXmlHelper.findColChar(r);
        curColIdx = ParseExcelXmlHelper.colStrToInt(colChar);
        String cellType = attributes.getValue("t");
        if (cellType != null && cellType.equals("s")) {
            nextIsString = true;
        } else {
            nextIsString = false;
        }
    }

    protected void parseStartV(String qName, Attributes attributes) {

    }

    protected boolean isContinue() {
        return false;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (!isContinue()) {
            return;
        }
        if ("row".equals(qName)) {
            processEndRow();
        } else if ("c".equals(qName)) {
            processEndC();
        } else if ("v".equals(qName)) {
            processEndV();
        }
    }

    protected void processEndRow() {

    }

    protected void processEndC() {
        String value = content.toString();
        if (nextIsString) {
            int idx = Integer.parseInt(value);
            value = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
            nextIsString = false;
        }
        data.add(new Excel2007Cell().setColIdx(curColIdx).setValue(value));
    }

    protected void processEndV() {

    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (!isContinue()) {
            return;
        }
        content.append(ch, start, length);
    }
}
