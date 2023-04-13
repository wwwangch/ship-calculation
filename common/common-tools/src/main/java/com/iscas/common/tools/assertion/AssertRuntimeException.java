package com.iscas.common.tools.assertion;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义断言基础异常，继承{@link RuntimeException}<br/>
 * 自定义一些属性,定义这个属性{@link #msgDetail}可以在抛出异常的时候,<br/>
 * 定义一些详细描述,这些信息可以不告诉前台用户，但是可以方便调试<br/>
 * 经常有需要判断空值的情况，需要写if判断，很麻烦，使用断言方法简单一些
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/3/14 17:38
 * @since jdk1.8
 */
@SuppressWarnings("unused")
@Data
@EqualsAndHashCode(callSuper = true)
public class AssertRuntimeException extends RuntimeException {
    /**
     * 详细信息描述
     */
    private String msgDetail;

    public AssertRuntimeException() {
        super();
    }

    public AssertRuntimeException(String message) {
        super(message);
    }

    public AssertRuntimeException(String message, String msgDetail) {
        super(message);
        this.msgDetail = msgDetail;
    }

    public AssertRuntimeException(String message, String msgDetail, Throwable e) {
        super(message, e);
        this.msgDetail = msgDetail;
    }

    public AssertRuntimeException(String message, Throwable cause) {
        super(message, cause);
        this.msgDetail = cause.getMessage();
    }

    public AssertRuntimeException(Throwable cause) {
        super(cause);
    }

    protected AssertRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}