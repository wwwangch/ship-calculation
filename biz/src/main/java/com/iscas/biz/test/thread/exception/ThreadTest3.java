package com.iscas.biz.test.thread.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/6/14 17:54
 * @since jdk11
 */
public class ThreadTest3 {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadTest3.class);

    public static void main(String[] args) {
        Runnable runnable1 = () -> {
            System.out.println(5 / 0);
            System.out.println(11111);
        };
        Runnable runnable2 = () -> {
            int[] ints = new int[1];
            System.out.println(ints[2]);
        };
        CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler();
        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        thread1.setUncaughtExceptionHandler(customExceptionHandler);
        thread2.setUncaughtExceptionHandler(customExceptionHandler);
        thread1.start();
        thread2.start();
    }

    public static class CustomExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            LOGGER.error("线程执行异常", e);
        }
    }
}
