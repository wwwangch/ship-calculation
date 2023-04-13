package com.iscas.common.tools.assertion;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;

/**
 * 集合断言
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/3/14 17:29
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "rawtypes"})
public class AssertCollectionUtils {
    private AssertCollectionUtils(){}

    /**
     * 断言集合不为null，如果为null，抛出异常，msg为异常信息
     * @since jdk1.8
     * @date 2020/3/14
     * @param collection 待判断集合
     * @param msg 错误描述
     */
    public static void assertCollectionNotNull(Collection collection, String msg) {
        if (collection == null) {
            throw new AssertRuntimeException(msg);
        }
    }

    /**
     * 断言集合为null，如果不为null，抛出异常，msg为异常信息
     * @since jdk1.8
     * @date 2020/3/14
     * @param collection 待判断集合
     * @param msg 错误描述
     */
    public static void assertCollectionNull(Collection collection, String msg) {
        if (collection != null) {
            throw new AssertRuntimeException(msg);
        }
    }

    /**
     * 断言集合不是空集合，如果是空集合，抛出异常，msg为异常信息
     * @since jdk1.8
     * @date 2020/3/14
     * @param collection 待判断集合
     * @param msg 错误描述
     */
    public static void assertCollectionNotEmpty(Collection collection, String msg) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new AssertRuntimeException(msg);
        }
    }

    /**
     * 断言集合是空集合，如果不是空集合，抛出异常，msg为异常信息
     * @since jdk1.8
     * @date 2020/3/14
     * @param collection 待判断集合
     * @param msg 错误描述
     */
    public static void assertCollectionEmpty(Collection collection, String msg) {
        if (CollectionUtils.isNotEmpty(collection)) {
            throw new AssertRuntimeException(msg);
        }
    }
}