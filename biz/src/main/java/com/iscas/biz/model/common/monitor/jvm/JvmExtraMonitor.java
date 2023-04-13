package com.iscas.biz.model.common.monitor.jvm;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/3/2 17:20
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
@ToString
public class JvmExtraMonitor {
    /**
     * 虚拟机名称
     */
    private String name;

    /**
     * 虚拟机版本
     */
    private String version;

    /**
     * 厂商
     */
    private String vendor;

    /**
     * 启动时间
     */
    private String startTime;

    /**
     * 堆内存大小 单位默认为Gb
     */
    private String totalHeapMemory;

    /**
     * 老年代大小 单位默认为Gb
     */
    private String totalOldGenMemory;

    /**
     * 压缩类空间大小 单位默认为Gb
     */
    private String totalCompressedClassMemory;

    /**
     * 已卸载的类个数
     */
    private long unLoadedClassCount;
    /**
     * 线程峰值
     */
    private long peakThreadCount;
    /**
     * 线程总计
     */
    private long totalThread;
    /**
     * 守护线程数
     */
    private long daemonThreadCount;

    /**
     * 死锁线程数
     */
    private long deadLockedThreadCount;
}
