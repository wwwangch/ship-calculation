package com.iscas.base.biz.config.stomp;

/**
 * stomp注册异常
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/10 11:45
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class StompRegistryException extends RuntimeException {
    public StompRegistryException() {}
    public StompRegistryException(String message) {
        super(message);
    }
    public StompRegistryException(Throwable cause) {
        super(cause);
    }
    public StompRegistryException(String message, Throwable cause) {
        super(message, cause);
    }
}
