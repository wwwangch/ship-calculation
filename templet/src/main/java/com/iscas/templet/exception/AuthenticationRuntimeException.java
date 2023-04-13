package com.iscas.templet.exception;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/10/26 17:54
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class AuthenticationRuntimeException extends BaseRuntimeException {
    public AuthenticationRuntimeException() {
    }

    public AuthenticationRuntimeException(String message) {
        super(message);
    }

    public AuthenticationRuntimeException(String message, String msgDetail) {
        super(message, msgDetail);
    }

    public AuthenticationRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthenticationRuntimeException(Throwable cause) {
        super(cause);
    }

    public AuthenticationRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
