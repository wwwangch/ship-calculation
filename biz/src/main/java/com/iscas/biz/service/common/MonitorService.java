package com.iscas.biz.service.common;

import java.util.Map;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/3/2 14:29
 * @since jdk1.8
 */
public interface MonitorService {

    void saveData(Map<Class, Object> data, Map<Class, Object> extraData);

    Object getPhysicalData();

    Object getJvmData();
}
