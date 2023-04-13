package com.iscas.biz.test.thread.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/6/14 17:54
 * @since jdk11
 */
public class ThreadTest2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadTest2.class);

    public static void main(String[] args) {
        Runnable runnable = () -> {
            try {
                System.out.println(5 / 0);
                System.out.println(11111);
            } catch (Exception e) {
                LOGGER.error("执行异常", e);
            }
        };
        new Thread(runnable).start();
    }
}
