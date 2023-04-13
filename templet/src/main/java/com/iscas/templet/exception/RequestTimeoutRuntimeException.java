package com.iscas.templet.exception;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/09/10 17:54
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class RequestTimeoutRuntimeException extends BaseRuntimeException {
    public RequestTimeoutRuntimeException() {
    }

    public RequestTimeoutRuntimeException(String message) {
        super(message);
    }

    public RequestTimeoutRuntimeException(String message, String msgDetail) {
        super(message, msgDetail);
    }

    public RequestTimeoutRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestTimeoutRuntimeException(Throwable cause) {
        super(cause);
    }

    public RequestTimeoutRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
