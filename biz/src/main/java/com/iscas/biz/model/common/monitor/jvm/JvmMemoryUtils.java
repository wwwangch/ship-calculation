package com.iscas.biz.model.common.monitor.jvm;

import org.apache.commons.lang3.StringUtils;

import java.lang.management.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JvmMemoryUtils {
    private static MemoryMXBean memoryMXBean;
    private static MemoryPoolMXBean edenSpace;
    private static MemoryPoolMXBean survivorSpace;
    private static MemoryPoolMXBean oldGen;
    private static MemoryPoolMXBean metaSpace;
    private static MemoryPoolMXBean compressedClassSpace;
    private static List<MemoryPoolMXBean> codeCache = new ArrayList<>();

    private static MemoryUsage EMPTY = new MemoryUsage(0, 0, 0, 0);

    static {
        memoryMXBean = ManagementFactory.getMemoryMXBean();

        List<MemoryPoolMXBean> memoryPoolMXBeanList = ManagementFactory.getMemoryPoolMXBeans();
        Optional.ofNullable(memoryPoolMXBeanList)
                .filter(mxBeans -> mxBeans.size() > 0)
                .ifPresent(mxBeans -> mxBeans.stream()
                        .filter(mxBean -> StringUtils.isNotBlank(mxBean.getName()))
                        .forEach(mxBean -> {
                            String poolName = mxBean.getName();

                            //暂时适配了JDK8和JDK11，本地没有安装其他版本JDK
                            /**
                             * Code Cache :
                             *      JDK11: CodeHeap 'non-nmethods'、CodeHeap 'profiled nmethods'、CodeHeap 'non-profiled nmethods'
                             *      JDK8:  Code Cache
                             * Metaspace :
                             *      JDK11: Metaspace
                             *      JDK8:  Metaspace
                             * Eden Space :
                             *      JDK11: G1 Eden Space
                             *       JDK8: PS Eden Space
                             * Survivor Space :
                             *      JDK11: G1 Survivor Space
                             *       JDK8: PS Survivor Space
                             * Old Space :
                             *      JDK11: G1 Old Gen
                             *       JDK8: PS Old Gen
                             * Compressed Class Space :
                             *      JDK11: Compressed Class Space
                             *       JDK8: Compressed Class Space
                             */
                            // 官方JVM(HotSpot)提供的MemoryPoolMXBean
                            // JDK1.7/1.8 Eden区内存池名称： "Eden Space" 或  "PS Eden Space"、 “G1 Eden Space”(和垃圾收集器有关)
                            // JDK1.7/1.8 Survivor区内存池名称："Survivor Space" 或 "PS Survivor Space"、“G1 Survivor Space”(和垃圾收集器有关)
                            // JDK1.7  老区内存池名称： "Tenured Gen"
                            // JDK1.8  老区内存池名称："Old Gen" 或 "PS Old Gen"、“G1 Old Gen”(和垃圾收集器有关)
                            // JDK1.7  方法/永久区内存池名称： "Perm Gen" 或 "PS Perm Gen"(和垃圾收集器有关)
                            // JDK1.8  方法/永久区内存池名称："Metaspace"(注意：不在堆内存中)
                            // JDK1.7/1.8  CodeCache区内存池名称： "Code Cache"
                            if (poolName.endsWith("Eden Space")) {
                                edenSpace = mxBean;
                            } else if (poolName.endsWith("Survivor Space")) {
                                survivorSpace = mxBean;
                            } else if (poolName.endsWith("Old Gen")) {
                                oldGen = mxBean;
                            } else if (poolName.endsWith("Metaspace")) {
                                metaSpace = mxBean;
                            } else if (poolName.startsWith("CodeHeap") || poolName.startsWith("Code Cache")) {
                                codeCache.add(mxBean);
                            } else if (poolName.startsWith("Compressed Class Space")) {
                                compressedClassSpace = mxBean;
                            }
                        }));
    }


    /**
     * 获取堆内存情况
     */
    public static MemoryUsage getHeapMemoryUsage() {
        return Optional.ofNullable(memoryMXBean)
                .map(MemoryMXBean::getHeapMemoryUsage)
                .orElse(EMPTY);
    }

    /**
     * 获取堆外内存情况
     */
    public static MemoryUsage getNonHeapMemoryUsage() {
        return Optional.ofNullable(memoryMXBean)
                .map(MemoryMXBean::getNonHeapMemoryUsage)
                .orElse(EMPTY);
    }

    /**
     * 获取Eden区内存情况
     */
    public static MemoryUsage getEdenSpaceMemoryUsage() {
        return getMemoryPoolUsage(edenSpace);
    }

    /**
     * 获取Eden区内存峰值（从启动或上一次重置开始统计），并重置
     */
    public static MemoryUsage getAndResetEdenSpaceMemoryPeakUsage() {
        return getAndResetMemoryPoolPeakUsage(edenSpace);
    }

    /**
     * 获取Survivor区内存情况
     */
    public static MemoryUsage getSurvivorSpaceMemoryUsage() {
        return getMemoryPoolUsage(survivorSpace);
    }

    /**
     * 获取Survivor区内存峰值（从启动或上一次重置开始统计），并重置
     */
    public static MemoryUsage getAndResetSurvivorSpaceMemoryPeakUsage() {
        return getAndResetMemoryPoolPeakUsage(survivorSpace);
    }

    /**
     * 获取老年代区内存情况
     */
    public static MemoryUsage getOldGenMemoryUsage() {
        return getMemoryPoolUsage(oldGen);
    }

    /**
     * 获取老区内存峰值（从启动或上一次重置开始统计），并重置
     */
    public static MemoryUsage getAndResetOldGenMemoryPeakUsage() {
        return getAndResetMemoryPoolPeakUsage(oldGen);
    }

    /**
     * 获取元空间内存情况
     */
    public static MemoryUsage getMetaSpaceMemoryUsage() {
        return getMemoryPoolUsage(metaSpace);
    }

    /**
     * 获取元空间内存峰值（从启动或上一次重置开始统计），并重置
     */
    public static MemoryUsage getAndResetMetaSpaceMemoryPeakUsage() {
        return getAndResetMemoryPoolPeakUsage(metaSpace);
    }

    /**
     * 获取压缩空间内存情况
     */
    public static MemoryUsage getCompressedClassSpaceUsage() {
        return getMemoryPoolUsage(compressedClassSpace);
    }

    /**
     * 获取压缩空间内存峰值（从启动或上一次重置开始统计），并重置
     */
    public static MemoryUsage getAndResetCompressedClassSpacePeakUsage() {
        return getAndResetMemoryPoolPeakUsage(compressedClassSpace);
    }

    /**
     * 获取CodeCache区内存情况
     */
    public static List<MemoryUsage> getCodeCacheMemoryUsage() {
        return Optional.ofNullable(codeCache)
                .map(caches -> caches.stream().map(cache -> getMemoryPoolUsage(cache)).toList())
                .orElse(Collections.emptyList());
    }

    /**
     * 获取CodeCache区内存峰值（从启动或上一次重置开始统计），并重置
     */
    public static List<MemoryUsage> getAndResetCodeCacheMemoryPeakUsage() {
        return Optional.ofNullable(codeCache)
                .map(caches -> caches.stream().map(cache -> getAndResetMemoryPoolPeakUsage(cache)).toList())
                .orElse(Collections.emptyList());
    }

    private static MemoryUsage getMemoryPoolUsage(MemoryPoolMXBean memoryPoolMXBean) {
        return Optional.ofNullable(memoryPoolMXBean)
                .map(MemoryPoolMXBean::getUsage)
                .orElse(EMPTY);
    }

    private static MemoryUsage getAndResetMemoryPoolPeakUsage(MemoryPoolMXBean memoryPoolMXBean) {
        return Optional.ofNullable(memoryPoolMXBean)
                .map(MemoryPoolMXBean::getPeakUsage)
                .map(usage -> {
                    memoryPoolMXBean.resetPeakUsage();
                    return usage;
                })
                .orElse(EMPTY);
    }

    public static void main(String[] args) {
        MemoryUsage heapMemoryUsage = getHeapMemoryUsage();
        MemoryUsage nonHeapMemoryUsage = getNonHeapMemoryUsage();
        MemoryUsage edenSpaceMemoryUsage = getAndResetEdenSpaceMemoryPeakUsage();
        MemoryUsage survivorSpaceMemoryUsage = getAndResetSurvivorSpaceMemoryPeakUsage();
        MemoryUsage oldGenMemoryUsage = getAndResetOldGenMemoryPeakUsage();
        MemoryUsage metaSpaceMemoryUsage = getAndResetMetaSpaceMemoryPeakUsage();
        MemoryUsage compressedClassSpaceUsage = getCompressedClassSpaceUsage();


        List<MemoryUsage> codeCacheMemoryUsage = getAndResetCodeCacheMemoryPeakUsage();


        System.out.println("*********************");
    }
}
