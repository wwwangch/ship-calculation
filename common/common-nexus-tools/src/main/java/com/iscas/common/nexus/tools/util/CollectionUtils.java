package com.iscas.common.nexus.tools.util;

import java.util.Collection;
import java.util.Objects;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/1/19 10:01
 */
public class CollectionUtils {
    private CollectionUtils() {}

    public static boolean isEmpty(Collection collection) {
        return Objects.isNull(collection) || collection.size() == 0;
    }

    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }
}
