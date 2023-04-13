package com.iscas.templet.exception;

import lombok.Getter;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/8/26 20:59
 * @since jdk1.8
 */
@SuppressWarnings("unused")
@Getter
public class HeaderException extends BaseException {
    /**详细信息描述*/
    private String msgDetail;
    public HeaderException() {
        super();
    }

    public HeaderException(String message) {
        super(message);
    }
    public HeaderException(String message, String msgDetail) {
        super(message);
        this.msgDetail = msgDetail;
    }

    public HeaderException(String message, String msgDetail, Throwable e) {
        super(message, e);
        this.msgDetail = msgDetail;
    }

    public HeaderException(String message, Throwable cause) {
        super(message, cause);
        this.msgDetail = cause.getMessage();
    }

    public HeaderException(Throwable cause) {
        super(cause);
    }

    protected HeaderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
