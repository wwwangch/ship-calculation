package com.iscas.common.tools.assertion;

import org.apache.commons.collections.MapUtils;

import java.util.Map;

/**
 * Map断言
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/3/14 17:29
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "rawtypes"})
public class AssertMapUtils {
    private AssertMapUtils(){}

    /**
     * 断言Map不是空，如果是空，抛出异常，msg为异常信息
     * @since jdk1.8
     * @date 2020/3/14
     * @param map 待判断map
     * @param msg 错误描述
     */
    public static void assertMapNotNull(Map map, String msg) {
        if (map == null) {
            throw new AssertRuntimeException(msg);
        }
    }

    /**
     * 断言Map是空，如果不是空，抛出异常，msg为异常信息
     * @since jdk1.8
     * @date 2020/3/14
     * @param map 待判断map
     * @param msg 错误描述
     */
    public static void assertMapNull(Map map, String msg) {
        if (map != null) {
            throw new AssertRuntimeException(msg);
        }
    }

    /**
     * 断言Map不是空Map，如果是空Map，抛出异常，msg为异常信息
     * @since jdk1.8
     * @date 2020/3/14
     * @param map 待判断map
     * @param msg 错误描述
     */
    public static void assertMapNotEmpty(Map map, String msg) {
        if (MapUtils.isEmpty(map)) {
            throw new AssertRuntimeException(msg);
        }
    }

    /**
     * 断言Map是空Map，如果不是空Map，抛出异常，msg为异常信息
     * @since jdk1.8
     * @date 2020/3/14
     * @param map 待判断map
     * @param msg 错误描述
     */
    public static void assertMapEmpty(Map map, String msg) {
        if (MapUtils.isNotEmpty(map)) {
            throw new AssertRuntimeException(msg);
        }
    }
}
