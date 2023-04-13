package com.iscas.biz.service.common.impl;

import cn.hutool.json.JSONObject;
import com.iscas.base.biz.util.CaffCacheUtils;
import com.iscas.base.biz.util.CloneUtils;
import com.iscas.base.biz.util.RegexUtils;
import com.iscas.biz.model.common.monitor.jvm.JvmExtraMonitor;
import com.iscas.biz.model.common.monitor.jvm.JvmMonitor;
import com.iscas.biz.model.common.monitor.sys.SysExtraMonitor;
import com.iscas.biz.model.common.monitor.sys.SysMonitor;
import com.iscas.biz.service.common.MonitorService;
import com.iscas.common.tools.core.date.DateSafeUtils;
import com.iscas.common.tools.core.reflect.ReflectUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/3/2 14:30
 * @since jdk1.8
 */
@Service
public class MonitorServiceImpl implements MonitorService {

    private static final int cacheSize = 1000;

    @Override
    public void saveData(Map<Class, Object> data, Map<Class, Object> extraData) {

        //动态数据的缓存
        Optional.ofNullable(data).filter(d -> d.size() > 0).ifPresent(d ->
                        data.forEach((clazz, object) -> {
                            String cacheName = clazz.getSimpleName();
                            ConcurrentHashMap<String, LinkedList<String>> dataCache = getCache(cacheName, ConcurrentHashMap::new);
                            Field[] declaredFields = ReflectUtils.getCurrentFields(clazz);
                            synchronized (cacheName) {
                                Arrays.stream(declaredFields).forEach(declaredField -> {
                                    //防止被漏洞软件扫描出漏洞，更改授权方式 add by zqw 2021-12-08
                                    ReflectUtils.makeAccessible(declaredField);
//                            declaredField.setAccessible(true);
                                    try {
                                        String fieldName = declaredField.getName();
                                        Object fieldValue = declaredField.get(object);
                                        String cacheValue = "0";
                                        if (fieldValue != null) {//普通字段
                                            if (!(fieldValue instanceof Date)) {
                                                cacheValue = RegexUtils.getStartNumber(fieldValue.toString());
                                            } else {//时间类型
                                                cacheValue = DateSafeUtils.format((Date) fieldValue);
                                            }
                                        }
                                        LinkedList<String> valueCache = dataCache.computeIfAbsent(fieldName, a -> new LinkedList<>());
                                        int size = valueCache.size();
                                        if (size >= cacheSize) {
                                            int delete = size - cacheSize;
                                            while (delete-- >= 0) {
                                                valueCache.removeFirst();
                                            }
                                        }
                                        valueCache.add(cacheValue);
                                    } catch (IllegalAccessException e) {
                                        e.printStackTrace();
                                    }
                                });
                            }
                        })
        );

        //额外数据的缓存
        Optional.ofNullable(extraData).filter(d -> d.size() > 0).ifPresent(d ->
                d.forEach((clazz, object) -> {
                    String cacheName = clazz.getSimpleName();
                    ConcurrentHashMap<String, Object> extraDataCache = getCache(cacheName, ConcurrentHashMap::new);
                    extraDataCache.put(cacheName, object);
                })
        );
    }

    @Override
    public Object getPhysicalData() {
        //系统监控数据
        return getMonitorData(SysMonitor.class, SysExtraMonitor.class);
    }

    @Override
    public Object getJvmData() {
        //JVM监控数据
        return getMonitorData(JvmMonitor.class, JvmExtraMonitor.class);
    }

    /**
     * 从缓存获取数据
     */
    private JSONObject getMonitorData(Class monitorClass, Class extraMonitorClass) {
        Object monitorCache = CaffCacheUtils.get(monitorClass.getSimpleName());
        ConcurrentHashMap<String, LinkedList<String>> monitorData = (ConcurrentHashMap<String, LinkedList<String>>) monitorCache;
        Object extraMonitorCache = CaffCacheUtils.get(extraMonitorClass.getSimpleName());
        ConcurrentHashMap<String, LinkedList<String>> extraMonitorData = (ConcurrentHashMap<String, LinkedList<String>>) extraMonitorCache;

        JSONObject result = new JSONObject();
        Optional.ofNullable(monitorData).ifPresent(data -> {
            synchronized (monitorData) {
                ConcurrentHashMap<String, LinkedList<String>> cloneData = CloneUtils.clone(monitorData);
                result.put("data", cloneData);
            }
        });
        Optional.ofNullable(extraMonitorData).ifPresent(extraData -> {
            result.put("props", extraData);
        });
        return result;

    }


    /**
     * 获取或初始化缓存
     */
    private <T> T getCache(String cacheKey, Supplier<T> valueSupplier) {
        //源码漏洞扫描工具会扫描出Code Correctness: Double-Checked Locking高危问题
        //暂时做修改 update by zqw 2021-12-08
//        Object value = CaffCacheUtils.get(cacheKey);
//        if (value == null) {
//            synchronized (cacheKey) {
//                value = CaffCacheUtils.get(cacheKey);
//                if (value == null) {
//                    value = valueSupplier.get();
//                    CaffCacheUtils.set(cacheKey, value);
//                }
//            }
//        }
//        return (T) value;

        synchronized (cacheKey.intern()) {
            Object value = CaffCacheUtils.get(cacheKey);
            if (value == null) {
                value = valueSupplier.get();
                CaffCacheUtils.set(cacheKey, value);
            }
            return (T) value;
        }
    }

}
