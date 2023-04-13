package com.iscas.biz.mp.config.db;

/**
 * 动态表名处理
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/4/11 13:28
 * @since jdk11
 */
public class DynamicTableNameHolder {
    private static final ThreadLocal<String> TABLE_NAME_HOLDER = new ThreadLocal<>();

    public static String get() {
        return TABLE_NAME_HOLDER.get();
    }

    public static void set(String tableName) {
        TABLE_NAME_HOLDER.set(tableName);
    }

    public static void remove() {
        TABLE_NAME_HOLDER.remove();
    }
}
