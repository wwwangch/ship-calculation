package com.iscas.biz.test.thread.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/6/14 17:54
 * @since jdk11
 */
public class ThreadTest5 {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadTest5.class);

    public static void main(String[] args) {
        Runnable runnable1 = () -> {
            System.out.println(5 / 0);
            System.out.println(11111);
        };
        ThreadGroup threadGroup = new ThreadGroup("threadGroup") {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                LOGGER.error("全局线程异常处理：线程执行异常", e);
            }
        };

        Thread thread1 = new Thread(threadGroup, runnable1);
        thread1.start();
    }
}
