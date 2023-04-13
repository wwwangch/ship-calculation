package com.iscas.common.rocksdb.tools;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/12/27 17:06
 * @since jdk1.8
 */
public class ArrayUtils {
    private ArrayUtils() {}

    public static boolean isEmpty(byte[] ts) {
        return ts == null || ts.length == 0;
    }
}
