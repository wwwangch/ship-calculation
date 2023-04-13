package com.iscas.biz.model.common.monitor.sys;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/1/18 16:18
 * @since jdk1.8
 * 内存使用情况
 */
@Data
@Accessors(chain = true)
public class Memory {

    /**
     * 总的物理内存
     */
    private long total;

    /**
     * 空闲的物理内存
     */
    private long free;
    /**
     * 已使用的物理内存
     */
    private long used;
    /**
     * 内存使用率
     */
    private String memoryUseRate;
}
