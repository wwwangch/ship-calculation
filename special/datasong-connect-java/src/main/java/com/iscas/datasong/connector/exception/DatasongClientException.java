package com.iscas.datasong.connector.exception;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/3 8:52
 * @since jdk11
 */
@SuppressWarnings("unused")
public class DatasongClientException extends Exception {
    public DatasongClientException() {
        super();
    }

    public DatasongClientException(String message) {
        super(message);
    }

    public DatasongClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatasongClientException(Throwable cause) {
        super(cause);
    }

    protected DatasongClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
