package com.liuyu.common.excel;

/**
 * ClassName: ExcelFun <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-7-3 下午1:33 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class ExcelFun {
    private StringBuilder text = new StringBuilder();

    public static ExcelFun instance(){
        return new ExcelFun();
    }
    public ExcelFun append(String text){
        this.text.append(text);
        return this;
    }

    @Override
    public String toString(){
        return text.toString();
    }
}
