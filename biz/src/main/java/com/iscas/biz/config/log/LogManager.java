package com.iscas.biz.config.log;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/2/20 17:24
 * @since jdk1.8
 */
@SuppressWarnings("AlibabaThreadShouldSetName")
public class LogManager {
    private static final int OPERATE_DELAY_TIME = 10;

    private static final ScheduledThreadPoolExecutor EXECUTOR = new ScheduledThreadPoolExecutor(10);

    private LogManager() {
    }

    public static void executeLog(Runnable task) {
        EXECUTOR.schedule(task, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
    }
}
