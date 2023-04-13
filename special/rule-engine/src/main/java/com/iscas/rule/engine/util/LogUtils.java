package com.iscas.rule.engine.util;

import org.slf4j.Logger;

/**
 * 为了不与上层模块日志冲突，做一个判断处理的工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/24 14:15
 * @since jdk1.8
 */
@SuppressWarnings("JavadocDeclaration")
public class LogUtils {
    private LogUtils() {}

    public static void info(Logger logger, String msg) {
        if (logger.isInfoEnabled()) {
            logger.info(msg);
        } else {
            System.out.println(msg);
        }
    }

    public static void debug(Logger logger, String msg) {
        if (logger.isDebugEnabled()) {
            logger.debug(msg);
        } else {
            System.out.println(msg);
        }
    }
}
