package com.iscas.templet.exception;

import java.sql.SQLFeatureNotSupportedException;
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 异常创建工具
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/7/4 15:53
 * @since jdk11
 */
public class Exceptions {
    private static final Pattern PATTERN = Pattern.compile("\\{\\}");

    private Exceptions() {
    }

    public static HeaderException formatHeaderException(Throwable e, String formatter, Object vals) {
        String message = replaceFormat(formatter, vals);
        return new HeaderException(message, e);
    }

    public static HeaderException formatHeaderException(String formatter, Object vals) {
        String message = replaceFormat(formatter, vals);
        return new HeaderException(message);
    }

    public static SQLFeatureNotSupportedException sqlFeatureNotSupportedException() {
        return new SQLFeatureNotSupportedException();
    }

    public static ExceptionInInitializerError exceptionInInitializerError(Throwable e) {
        return new ExceptionInInitializerError(e);
    }

    public static IllegalStateException illegalStateException(String msg) {
        return new IllegalStateException(msg);
    }

    public static IllegalStateException illegalStateException(Throwable e) {
        return new IllegalStateException(e);
    }

    public static IllegalArgumentException illegalArgumentException(String msg) {
        return new IllegalArgumentException(msg);
    }

    public static ValidTokenException validTokenException(String message) {
        return new ValidTokenException(message);
    }

    public static ValidTokenException validTokenException(String message, String msgDetail) {
        return new ValidTokenException(message, msgDetail);
    }

    public static ValidTokenException validTokenException(String message, String msgDetail, Throwable e) {
        return new ValidTokenException(message, msgDetail, e);
    }

    public static AuthenticationRuntimeException authenticationRuntimeException(String message, Throwable e) {
        return new AuthenticationRuntimeException(message, e);
    }

    public static AuthenticationRuntimeException authenticationRuntimeException(String message, String msgDetail) {
        return new AuthenticationRuntimeException(message, msgDetail);
    }

    public static AuthenticationRuntimeException authenticationRuntimeException(String message) {
        return new AuthenticationRuntimeException(message);
    }

    public static RequestTimeoutRuntimeException requestTimeoutRuntimeException(String message) {
        return new RequestTimeoutRuntimeException(message);
    }

    public static RepeatSubmitException repeatSubmitException(String message, String msgDetail) {
        return new RepeatSubmitException(message, msgDetail);
    }

    public static RepeatSubmitException repeatSubmitException(String message) {
        return new RepeatSubmitException(message);
    }

    public static AuthorizationException authorizationException(String message, String msgDetail) {
        return new AuthorizationException(message, msgDetail);
    }

    public static AuthorizationException authorizationException(String message) {
        return new AuthorizationException(message);
    }

    public static AuthorizationRuntimeException authorizationRuntimeException(String message, String msgDetail) {
        return new AuthorizationRuntimeException(message, msgDetail);
    }

    public static AuthorizationRuntimeException authorizationRuntimeException(String message) {
        return new AuthorizationRuntimeException(message);
    }

    public static LoginException loginException(String message) {
        return new LoginException(message);
    }

    public static LoginException loginException(String message, Throwable e) {
        return new LoginException(message, e);
    }

    public static BaseException baseException() {
        return new BaseException();
    }

    public static BaseException baseException(String message) {
        return new BaseException(message);
    }

    public static BaseException baseException(String message, String msgDetail) {
        return new BaseException(message, msgDetail);
    }

    public static BaseException baseException(String message, String msgDetail, Throwable e) {
        return new BaseException(message, msgDetail, e);
    }

    public static BaseException baseException(String message, Throwable cause) {
        return new BaseException(message, cause);
    }

    public static BaseException baseException(Throwable cause) {
        return new BaseException(cause);
    }

    public static BaseRuntimeException baseRuntimeException() {
        return new BaseRuntimeException();
    }

    public static BaseRuntimeException baseRuntimeException(String message) {
        return new BaseRuntimeException(message);
    }

    public static BaseRuntimeException baseRuntimeException(String message, String msgDetail) {
        return new BaseRuntimeException(message, msgDetail);
    }

    public static BaseRuntimeException baseRuntimeException(String message, String msgDetail, Throwable e) {
        return new BaseRuntimeException(message, msgDetail, e);
    }

    public static BaseRuntimeException baseRuntimeException(String message, Throwable cause) {
        return new BaseRuntimeException(message, cause);
    }

    public static BaseRuntimeException baseRuntimeException(Throwable cause) {
        return new BaseRuntimeException(cause);
    }

    public static BaseException formatBaseException(String formatter, Object... formatVals) {
        String message = replaceFormat(formatter, formatVals);
        return new BaseException(message);
    }

    public static BaseException formatBaseException(Throwable e, String formatter, Object... formatVals) {
        String message = replaceFormat(formatter, formatVals);
        return new BaseException(message, e);
    }

    public static BaseRuntimeException formatBaseRuntimeException(String formatter, Object... formatVals) {
        String message = replaceFormat(formatter, formatVals);
        return new BaseRuntimeException(message);
    }

    public static BaseRuntimeException formatBaseRuntimeException(Throwable e, String formatter, Object... formatVals) {
        String message = replaceFormat(formatter, formatVals);
        return new BaseRuntimeException(message, e);
    }

    public static ValidDataException formatValidDataException(String formatter, Object... formatVals) {
        String message = replaceFormat(formatter, formatVals);
        return new ValidDataException(message);
    }

    public static RuntimeException formatRuntimeException(String formatter, Object... formatVals) {
        String message = replaceFormat(formatter, formatVals);
        return new RuntimeException(message);
    }

    public static RepeatSubmitException formatRepeatSubmitException(String formatter, Object... formatVals) {
        String message = replaceFormat(formatter, formatVals);
        return new RepeatSubmitException(message);
    }

    public static Exception exception(Throwable e) {
        return new Exception(e);
    }

    public static Exception exception(String message) {
        return new Exception(message);
    }

    public static RuntimeException runtimeException(Throwable e) {
        return new RuntimeException(e);
    }

    public static RuntimeException runtimeException(String message) {
        return new RuntimeException(message);
    }

    public static RuntimeException runtimeException(String message, Throwable e) {
        return new RuntimeException(message, e);
    }

    public static UnsupportedOperationException unsupportedOperationException(String message, Throwable e) {
        return new UnsupportedOperationException(message, e);
    }

    public static UnsupportedOperationException unsupportedOperationException(String message) {
        return new UnsupportedOperationException(message);
    }

    public static ValidDataException validDataException(String message) {
        return new ValidDataException(message);
    }

    public static UnsupportedOperationException formatUnsupportedOperationException(String formatter, Object... formatVals) {
        String message = replaceFormat(formatter, formatVals);
        return new UnsupportedOperationException(message);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Throwable> T formatException(Class<? extends Throwable> clazz, String formatter, Object... formatVals) {
        String message = replaceFormat(formatter, formatVals);
        try {
            return (T) clazz.getDeclaredConstructor(String.class).newInstance(message);
        } catch (Exception e) {
            throw Exceptions.runtimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends Throwable> T formatException(Class<? extends Throwable> clazz, Throwable e, String formatter, Object... formatVals) {
        String message = replaceFormat(formatter, formatVals);
        try {
            return (T) clazz.getDeclaredConstructor(String.class, Throwable.class).newInstance(message, e);
        } catch (Exception ex) {
            throw Exceptions.runtimeException(ex);
        }
    }

    private static String replaceFormat(String formatter, Object... formatVals) {
        Matcher matcher = PATTERN.matcher(formatter);
        int i = 0;
        while (matcher.find()) {
            formatter = matcher.replaceFirst("{" + i++ + "}");
            matcher = PATTERN.matcher(formatter);
        }
        return MessageFormat.format(formatter, formatVals);
    }
}
