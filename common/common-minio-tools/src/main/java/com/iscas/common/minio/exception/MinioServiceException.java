package com.iscas.common.minio.exception;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/6/4 17:11
 * @since jdk1.8
 */
public class MinioServiceException extends Exception {
    public MinioServiceException() {
        super();
    }

    public MinioServiceException(String message) {
        super(message);
    }

    public MinioServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public MinioServiceException(Throwable cause) {
        super(cause);
    }
}
