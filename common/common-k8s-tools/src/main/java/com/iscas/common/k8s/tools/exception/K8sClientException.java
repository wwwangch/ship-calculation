package com.iscas.common.k8s.tools.exception;

import com.iscas.templet.exception.BaseException;

/**
 * K8S客户端错误信息
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/2 14:05
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class K8sClientException extends BaseException {
    /**详细信息描述*/
    private String msgDetail;
    public K8sClientException() {
        super();
    }

    public K8sClientException(String message) {
        super(message);
    }
    public K8sClientException(String message, String msgDetail) {
        super(message);
        this.msgDetail = msgDetail;
    }

    public K8sClientException(String message, String msgDetail, Throwable e) {
        super(message, e);
        this.msgDetail = msgDetail;
    }

    public K8sClientException(String message, Throwable cause) {
        super(message, cause);
        this.msgDetail = cause.getMessage();
    }

    public K8sClientException(Throwable cause) {
        super(cause);
    }

    protected K8sClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
