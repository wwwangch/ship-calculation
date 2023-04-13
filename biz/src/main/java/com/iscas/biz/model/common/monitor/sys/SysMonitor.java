package com.iscas.biz.model.common.monitor.sys;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/1/18 16:00
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
@ToString
public class SysMonitor {
    /**
     * 采集时间
     */
    private Date collectTime;
    /**
     * 系统cpu负载
     */
    private String systemCpuLoad;
    /**
     * 核心cup平均负载
     */
    private String processorCpuLoad;
    /**
     * 内存使用率
     */
    private String memoryUseRate;
    /**
     * 磁盘使用率
     */
    private String spaceUseRate;
    /**
     * inodes使用率
     */
    private String inodesUseRate;
    /**
     * 单位时间内系统能处理的I/O请求数量
     */
    private long iops;
    /**
     * 磁盘吞吐量 单位 kb 、b、mb、gb？
     */
    private String throught;
    /**
     * 网络接收 单位 kbps
     */
    private String networkRecv;
    /**
     * 网络发送
     */
    private String networkSend;

}
