package com.iscas.biz.model.common.monitor.sys;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/1/18 15:06
 * @since jdk1.8
 * 从系统启动开始累计到当前时刻，cpu情况
 */
@Data
@Accessors(chain = true)
public class Cpu {

    /**
     * cpu核数
     */
    private int processorCount;
    /**
     * 总cup使用
     */
    private long total;
    /**
     * 用户态的CPU时间，不包含nice值为负进程
     */
    private long user;
    /**
     * nice值为负的进程所占用的CPU时间
     */
    private long nice;
    /**
     * 系统核心时间
     */
    private long system;
    /**
     * 除IO等待时间以外其它等待时间
     */
    private long idle;
    /**
     * IO等待时间
     */
    private long iowait;
    /**
     * 硬中断时间
     */
    private long irq;
    /**
     * 软中断时间
     */
    private long softirq;
    /**
     * 当一台物理机有多个虚拟机时，该cpu在其他虚拟机运行的时间
     */
    private long steal;

    /**
     * 系统cpu负载
     */
    private String systemCpuLoad;

    /**
     * 核心cup平均负载
     */
    private String processorCpuLoad;

}
