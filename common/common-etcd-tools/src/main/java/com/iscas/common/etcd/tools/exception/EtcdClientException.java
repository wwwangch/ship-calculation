package com.iscas.common.etcd.tools.exception;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/10/28 9:15
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class EtcdClientException extends RuntimeException {
    public EtcdClientException() {
        super();
    }

    public EtcdClientException(String message) {
        super(message);
    }

    public EtcdClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public EtcdClientException(Throwable cause) {
        super(cause);
    }
}
