package com.iscas.biz.model.common.monitor.sys;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/1/19 13:42
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class DiskStore {

    /**
     * disk name
     */
    private String name;
    /**
     * The number of bytes read from the disk
     */
    private long readBytes;

    /**
     * The number of bytes written to the disk
     */
    private long writeBytes;

    /**
     * The number of reads from the disk
     */
    private long reads;

    /**
     * The number of writes to the disk
     */
    private long writes;

    /**
     * 单位时间内系统能处理的I/O请求数量
     */
    private long iops;

    /**
     * 吞吐量,字节
     */
    private long throught;

}
