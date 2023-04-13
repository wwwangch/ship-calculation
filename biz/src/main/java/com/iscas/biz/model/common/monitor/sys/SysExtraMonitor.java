package com.iscas.biz.model.common.monitor.sys;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/1/22 9:42
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class SysExtraMonitor {
    /**
     * cpu核数
     */
    private int processorCount;
    /**
     * 总内存
     */
    private String totalMemory;
    /**
     * 磁盘大小
     */
    private String totalDisk;
}
