package com.iscas.biz.model.common.monitor.jvm;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/3/3 15:16
 * @since jdk1.8
 */
public class JvmThreadUtils {

    private static ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

    /**
     * 当前线程总量
     */
    public static int getThreadCount() {
        return threadMXBean.getThreadCount();
    }

    /**
     * Daemon线程总量
     */
    public static int getDaemonThreadCount() {
        return threadMXBean.getDaemonThreadCount();
    }

    /**
     * 线程总计
     */
    public static long getTotalThreadCount() {
        return threadMXBean.getTotalStartedThreadCount();
    }

    /**
     * 获取线程数量峰值
     */
    public static int getPeakThreadCount() {
        return threadMXBean.getPeakThreadCount();
    }

    /**
     * 获取线程数量峰值，并重置
     */
    public static int getAndResetPeakThreadCount() {
        int count = threadMXBean.getPeakThreadCount();
        threadMXBean.resetPeakThreadCount();
        return count;
    }

    /**
     * 死锁线程总量
     */
    public static int getDeadLockedThreadCount() {
        long[] deadlockedThreads = threadMXBean.findDeadlockedThreads();
        return Optional.ofNullable(deadlockedThreads)
                .map(threads -> threads.length)
                .orElse(0);
    }
}
