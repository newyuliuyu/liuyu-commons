package com.liuyu.common.file.reader.spi;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.List;

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
public class SheetNameHandler extends DefaultHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private List<String> sheetNames = Lists.newArrayList();
    private StringBuilder theSheetName;
    private int sheetSize = 0;
    private boolean beginProcessSheetNames = false;
    private boolean beginProcessOneSheetName = false;

    public List<String> getSheetNames() {
        return sheetNames;
    }

    public int getSheetSize() {
        return sheetSize;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("TitlesOfParts".equals(qName)) {
            beginProcessSheetNames = true;
        } else if (beginProcessSheetNames && "vt:vector".equals(qName)) {
            String size = attributes.getValue("size");
            sheetSize = Integer.parseInt(size);
            logger.debug("总共有有工作簿:{}个", size);
        } else if (beginProcessSheetNames && "vt:lpstr".equals(qName)) {
            beginProcessOneSheetName = true;
            theSheetName = new StringBuilder();
        }
    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("TitlesOfParts".equals(qName)) {
            beginProcessSheetNames = false;
            throw new GetSheetNameException("获取完毕工作名称");
        } else if (beginProcessSheetNames && "vt:vector".equals(qName)) {

        } else if (beginProcessSheetNames && "vt:lpstr".equals(qName)) {
            beginProcessOneSheetName = false;
            sheetNames.add(theSheetName.toString());
        }
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (beginProcessOneSheetName) {
            theSheetName.append(ch, start, length);
        }
    }
}
