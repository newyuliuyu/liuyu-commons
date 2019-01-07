package com.liuyu.common.excel;

import org.junit.Test;

/**
 * ClassName: ExcelTableTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 19-1-7 下午3:21 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class ExcelTableTest {


    @Test
    public void createTbale() throws Exception {
        ExcelTable table = new ExcelTable();
        table.addSheet("test");
        table.createRowAndCells(0, "男", "2", "3");
        table.createDropDownMenu(1, 1, 0, 0, new String[]{"男", "女"});
//        Sheet sheet = table.getSheet();
//        DataValidationHelper helper = sheet.getDataValidationHelper();
//        DataValidationConstraint constraint = helper.createExplicitListConstraint(new String[]{"男","女"});
//        CellRangeAddressList addressList = new CellRangeAddressList(0,0,0,0);
//        DataValidation dataValidation = helper.createValidation(constraint, addressList);
//        //处理Excel兼容性问题
//        if(dataValidation instanceof XSSFDataValidation) {
//            dataValidation.setSuppressDropDownArrow(true);
//            dataValidation.setShowErrorBox(true);
//        }else {
//            dataValidation.setSuppressDropDownArrow(false);
//        }
//
//        sheet.addValidationData(dataValidation);

        table.save("/home/liuyu/tmp/excle/a.xls");

    }

}