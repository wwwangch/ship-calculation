package com.iscas.common.tools.assertion;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串断言
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/3/14 17:29
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class AssertStrUtils {
    private AssertStrUtils(){}

    /**
     * 断言字符串不为null，如果为null，抛出异常，msg为异常信息
     * @since jdk1.8
     * @date 2020/3/14
     * @param str 待判断字符串
     * @param msg 错误描述
     */
    public static void assertStrNotNull(String str, String msg) {
        if (str == null) {
            throw new AssertRuntimeException(msg);
        }
    }

    /**
     * 断言字符串为null，如果不为null，抛出异常，msg为异常信息
     * @since jdk1.8
     * @date 2020/3/14
     * @param str 待判断字符串
     * @param msg 错误描述
     */
    public static void assertStrNull(String str, String msg) {
        if (str != null) {
            throw new AssertRuntimeException(msg);
        }
    }

    /**
     * 断言字符串不为空字符串穿，如果为空字符串，抛出异常，msg为异常信息
     * @since jdk1.8
     * @date 2020/3/14
     * @param str 待判断字符串
     * @param msg 错误描述
     */
    public static void assertStrNotEmpty(String str, String msg) {
        if (StringUtils.isEmpty(str)) {
            throw new AssertRuntimeException(msg);
        }
    }

    /**
     * 断言字符串为空字符串穿，如果不为空字符串，抛出异常，msg为异常信息
     * @since jdk1.8
     * @date 2020/3/14
     * @param str 待判断字符串
     * @param msg 错误描述
     */
    public static void assertStrEmpty(String str, String msg) {
        if (StringUtils.isNotEmpty(str)) {
            throw new AssertRuntimeException(msg);
        }
    }
}