package com.iscas.biz.mp.config.db;

import java.util.LinkedList;
import java.util.Objects;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/5/10 13:46
 * @since jdk1.8
 */
public class DbContextHolder {
    private static final ThreadLocal<LinkedList<String>> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 设置数据源
     *
     * @param dbType dbType
     */
    public static void setDbType(String dbType) {
        LinkedList<String> dbTypeStack = CONTEXT_HOLDER.get();
        if (dbTypeStack == null) {
            dbTypeStack = new LinkedList<>();
            CONTEXT_HOLDER.set(dbTypeStack);
        }
        dbTypeStack.push(dbType);
    }

    /**
     * 取得当前数据源
     *
     * @return String
     */
    public static String getDbType() {
        LinkedList<String> dbTypeStack = CONTEXT_HOLDER.get();
        if (!Objects.isNull(dbTypeStack) && dbTypeStack.size() > 0) {
            return dbTypeStack.peek();
        }
        return null;
    }

    /**
     * 清除上下文数据
     */
    public static void clearDbType() {
        LinkedList<String> dbTypeStack = CONTEXT_HOLDER.get();
        if (Objects.isNull(dbTypeStack) || dbTypeStack.size() <= 1) {
            CONTEXT_HOLDER.remove();
        } else {
            dbTypeStack.removeFirst();
        }
    }

}

