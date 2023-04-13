package com.iscas.common.etcd.tools.utils;

import java.util.Collection;
import java.util.Map;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/10/28 9:18
 * @since jdk1.8
 */
@SuppressWarnings({"rawtypes", "unused", "BooleanMethodIsAlwaysInverted"})
public class CollectionUtils {
    private CollectionUtils() {}

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.size() == 0;
    }

    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    public static boolean isEmpty(Map map) {
        return map == null || map.size() == 0;
    }

    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }
}
