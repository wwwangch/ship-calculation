package com.iscas.biz.test.thread.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/6/14 17:54
 * @since jdk11
 */
public class ThreadPoolSchedulerTest2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPoolSchedulerTest2.class);
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        executor.scheduleAtFixedRate(() -> {
            try {
                System.out.println(111);
                System.out.println(4 / 0);
            } catch (Exception e) {
                LOGGER.error("执行出错", e);
            }
        }, 1, 2, TimeUnit.SECONDS);
    }
}
