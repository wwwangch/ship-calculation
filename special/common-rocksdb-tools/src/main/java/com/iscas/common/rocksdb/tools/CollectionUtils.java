package com.iscas.common.rocksdb.tools;

import java.util.Collection;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/12/27 17:25
 * @since jdk1.8
 */
public class CollectionUtils {
    private CollectionUtils() {}

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.size() == 0;
    }
}
