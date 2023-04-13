package com.iscas.common.harbor.tools.exception;

/**
 * 调用Harbor的错误
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/8 21:52
 * @since jdk1.8
 */
public class CallHarborException extends Exception {
    public CallHarborException() {
        super();
    }

    public CallHarborException(String message) {
        super(message);
    }

    public CallHarborException(String message, Throwable cause) {
        super(message, cause);
    }

    public CallHarborException(Throwable cause) {
        super(cause);
    }

    protected CallHarborException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
