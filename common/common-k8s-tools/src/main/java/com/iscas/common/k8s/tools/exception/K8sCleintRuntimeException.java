package com.iscas.common.k8s.tools.exception;

import com.iscas.templet.exception.BaseRuntimeException;
import lombok.Getter;
import lombok.Setter;

/**

 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/16 21:17
 * @since jdk1.8
 */
@SuppressWarnings("unused")
@Getter
@Setter
public class K8sCleintRuntimeException extends BaseRuntimeException {
    /**详细信息描述*/
    private String msgDetail;
    public K8sCleintRuntimeException() {
        super();
    }

    public K8sCleintRuntimeException(String message) {
        super(message);
    }
    public K8sCleintRuntimeException(String message, String msgDetail) {
        super(message);
        this.msgDetail = msgDetail;
    }

    public K8sCleintRuntimeException(String message, String msgDetail, Throwable e) {
        super(message, e);
        this.msgDetail = msgDetail;
    }

    public K8sCleintRuntimeException(String message, Throwable cause) {
        super(message, cause);
        this.msgDetail = cause.getMessage();
    }

    public K8sCleintRuntimeException(Throwable cause) {
        super(cause);
    }

    protected K8sCleintRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
