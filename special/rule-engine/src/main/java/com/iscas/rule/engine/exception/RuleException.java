package com.iscas.rule.engine.exception;

/**
 * 自定义规则异常类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/10/20 15:19
 * @since jdk1.8
 */
@SuppressWarnings({"JavadocDeclaration", "unused"})
public class RuleException extends Exception {

    public RuleException() {
        super();
    }

    public RuleException(String message) {
        super(message);
    }

    public RuleException(String message, Throwable e) {
        super(message, e);
    }
    public RuleException(Throwable cause) {
        super(cause);
    }

    protected RuleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
