package com.iscas.biz.model.common.monitor.jvm;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.lang.management.MemoryUsage;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/3/2 17:22
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
@ToString
public class JvmMemory {
    /**
     * 初始内存
     */
    private long init;
    /**
     * 已使用内存
     */
    private long used;
    /**
     * 已经申请分配的内存
     */
    private long committed;
    /**
     * 最大内存
     */
    private long max;
    /**
     * 已经申请分配内存与最大内存百分比
     */
    private float memoryUseRate;


    public JvmMemory() {
    }

    public JvmMemory(MemoryUsage memoryUsage) {
        this(memoryUsage.getInit(), memoryUsage.getUsed(), memoryUsage.getCommitted(), memoryUsage.getMax());
    }

    public JvmMemory(long init, long used, long committed, long max) {
        this.setMemoryUsage(init, used, committed, max);
    }

    private void setMemoryUsage(long init, long used, long committed, long max) {
        this.init = init;
        this.used = used;
        this.committed = committed;
        this.max = max;
        if (this.used > 0 && max > 0) {
            this.memoryUseRate = used * Float.valueOf("1.0") / max;
        } else {
            this.memoryUseRate = 0;
        }
    }

}
