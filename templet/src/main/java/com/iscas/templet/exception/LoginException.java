package com.iscas.templet.exception;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/9/5 15:39
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class LoginException extends BaseException {
    public LoginException() {
    }

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, String msgDetail) {
        super(message, msgDetail);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginException(Throwable cause) {
        super(cause);
    }

    public LoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
