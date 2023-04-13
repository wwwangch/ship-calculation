package com.iscas.biz.model.common.monitor.sys;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/1/18 16:37
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class Disk {

    /**
     *分区名
     */
    private String fileSystem;
    /**
     * 文件系统类型
     */
    private String type;
    /**
     * 分区大小
     */
    private long total;
    /**
     * 已用的空间大小
     */
    private long used;
    /**
     * 可用的空间大小
     */
    private long free;
    /**
     * 空间使用率
     */
    private String spaceUseRate;
    /**
     * 挂载路径
     */
    private String mountedOn;

    /**
     * inodes总大小
     */
    private long totalInodes;

    /**
     * inodes可用大小
     */
    private long freeInodes;

    /**
     * inodes已用大小
     */
    private long usedInodes;
    /**
     * inodes使用率
     */
    private String inodesUseRate;
}
