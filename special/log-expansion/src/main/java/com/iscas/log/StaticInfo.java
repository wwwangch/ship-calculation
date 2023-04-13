package com.iscas.log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/3/6 13:31
 * @since jdk1.8
 */
public class StaticInfo {
    public static final BlockingQueue<QueueEntity> LOG_QUEUE = new LinkedBlockingQueue<>();

    public static final ExecutorService logThreadPool = Executors.newFixedThreadPool(2);

    public static boolean logThreadFlag = false;

}
