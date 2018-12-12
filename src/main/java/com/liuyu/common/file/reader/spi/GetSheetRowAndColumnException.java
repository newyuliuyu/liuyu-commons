package com.liuyu.common.file.reader.spi;

/**
 * ClassName: SheetNameException <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 18-3-9 下午1:02 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class GetSheetRowAndColumnException extends RuntimeException {
    public GetSheetRowAndColumnException() {
        super();
    }

    public GetSheetRowAndColumnException(String message) {
        super(message);
    }

    public GetSheetRowAndColumnException(String message, Throwable cause) {
        super(message, cause);
    }

    public GetSheetRowAndColumnException(Throwable cause) {
        super(cause);
    }


    protected GetSheetRowAndColumnException(String message, Throwable cause,
                                            boolean enableSuppression,
                                            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
