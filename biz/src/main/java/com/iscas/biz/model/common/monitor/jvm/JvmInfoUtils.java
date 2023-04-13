package com.iscas.biz.model.common.monitor.jvm;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.CompilationMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Properties;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/3/3 14:32
 * @since jdk1.8
 */
public class JvmInfoUtils {
    private static RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
    private static ClassLoadingMXBean classLoad = ManagementFactory.getClassLoadingMXBean();
    private static CompilationMXBean compilation = ManagementFactory.getCompilationMXBean();
    private static Properties prop = System.getProperties();

    /**
     * 获取JVM名称
     */
    static public String getName() {
        return runtime.getVmName();
    }

    /**
     * 获取JVM版本
     */
    public static String getVersion() {
        return getSystemProperty("java.version");
    }

    /**
     * 获取JVM运营商
     */
    public static String getVendor() {
        return runtime.getVmVendor();
    }


    /**
     * 获取JVM启动时间
     */
    public static long getStartTime() {
        return runtime.getStartTime();
    }

    /**
     * 获取JVM运行时间
     */
    public static long getUpTime() {
        return runtime.getUptime();
    }

    /**
     * 获取JVM当前加载类总量
     */
    public static long getLoadedClassCount() {
        return classLoad.getLoadedClassCount();
    }
    /**
     * 获取JVM已卸载类总量
     */
    public static long getUnLoadedClassCount() {
        return classLoad.getUnloadedClassCount();
    }

    /**
     * 获取JVM从启动到现在加载类总量
     */
    public static long getTotalLoadedClassCount() {
        return classLoad.getTotalLoadedClassCount();
    }

    /**
     * 获取指定key的属性值
     */
     public static String getSystemProperty(String key) {
        return prop.getProperty(key);
    }

     public static Properties getSystemProperty() {
        return prop;
    }

}
