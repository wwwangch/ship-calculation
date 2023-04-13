package com.iscas.common.prometheus.tools.exception;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/3/30 16:10
 */
public class PrometheusException extends Exception {
    public PrometheusException() {
        super();
    }

    public PrometheusException(String message) {
        super(message);
    }

    public PrometheusException(String message, Throwable cause) {
        super(message, cause);
    }

    public PrometheusException(Throwable cause) {
        super(cause);
    }
}
