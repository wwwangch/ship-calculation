package com.iscas.biz.model.common.monitor.jvm;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;


/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/3/2 16:56
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
@ToString
public class JvmMonitor {

    /**
     * 采集时间
     */
    private Date collectTime;
    /**
     * 堆内存使用率
     */
    private String heapMemoryUseRate;
    /**
     * 压缩类空间使用率
     */
    private String compressedClassSpaceUseRate;
    /**
     * 热代码区使用率
     */
    private String codeCacheMemoryUseRate;


    /**
     * 非堆内存已使用大小  单位默认为Mb
     */
    private String noHeapMemoryUsage;
    /**
     * Eden区内存已使用大小 单位默认为Mb
     */
    private String edenSpaceUsage;
    /**
     * survivor区内存已使用大小 单位默认为Mb
     */
    private String survivorSpaceUsage;
    /**
     * 老年代区内存已使用大小 单位默认为Mb
     */
    private String oldGenSPaceUsage;
    /**
     * 元空间已使用大小 单位默认为Mb
     */
    private String metaSpaceUsage;

    /**
     * 已加载的类个数
     */
    private long loadedClassCount;
    /**
     * 活动线程数
     */
    private long activeThreadCount;
}
