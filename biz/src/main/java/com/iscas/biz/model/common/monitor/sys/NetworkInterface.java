package com.iscas.biz.model.common.monitor.sys;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/1/19 17:25
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class NetworkInterface {

    /**
     * 接口名
     */
    private String name;
    /**
     * 接口描述
     */
    private String displayName;
    /**
     * MTU
     */
    private int mtu;
    /**
     * MAC
     */
    private String mac;
    /**
     * IPv4
     */
    private String[] ipv4;

    /**
     * 子网掩码
     */
    private Short[] subnetMasks;
    /**
     * IPv6
     */
    private String[] ipv6;
    /**
     * 接收比特位
     */
    private long bytesRecv;
    /**
     * 发送比特位
     */
    private long bytesSent;
    /**
     * 接收数据包
     */
    private long packetsRecv;
    /**
     * I发送数据包
     */
    private long packetsSent;
}
