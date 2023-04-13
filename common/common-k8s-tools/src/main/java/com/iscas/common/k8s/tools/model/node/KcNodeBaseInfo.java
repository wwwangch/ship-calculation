package com.iscas.common.k8s.tools.model.node;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * 节点基本信息
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/2 16:27
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class KcNodeBaseInfo {
    /**
     * 名称
     * */
    private String name;

    /**
     * 创建时间
     * */
    private Date createTime;

    /**
     * 启动时间
     * */
    private String runTimeStr;

    /**
     * labels
     * */
    private List<String[]> labels;

    /**
     * annotations
     * */
    private List<String[]> annotations;

    /**
     * 污点
     * */
    private List<KcNodeBaseInfoTaint> taints;

    /**
     * CPU
     * */
    private double cpu = 0;

    /**
     * 已使用的CPU资源
     * */
    private double usedCpu = 0;

    /**
     * 内存
     * */
    private long memory = 0;

    /**
     * 已使用的内存资源
     * */
    private long usedMemory = 0;

    /**
     * 短暂存储
     * */
    private long transientStorage = 0;

    /**
     * 已使用的本地存储资源
     * */
    private long usedTransientStorage = 0;

    /**
     *  最大容器组数量
     * */
    private Integer maxPods = 0;

    /**
     * 已经使用的容器组
     * */
    private Integer usedPods = 0;

    /**
     * 容器组CIDR
     * */
    private String cidr;

    /**
     * IPTunnelAddr
     * */
    private String ipTunnelAddr;

    /**
     * InternalIP
     * */
    private String internalIP;

    /**
     * hostname
     * */
    private String hostname;

    /**
     *  操作系统
     * */
    private String os;

    /**
     * 体系架构
     * */
    private String architecture;

    /**
     * 操作系统镜像
     * */
    private String osImage;

    /**
     * 操作系统内核
     * */
    private String osKernel;

    /**
     * 容器引擎
     * */
    private String containerEngine;

    /**
     * kubelet版本
     * */
    private String kubeletVersion;

    /**
     * kube proxy版本
     * */
    private String kubeProxyVersion;

    /**
     * condition
     * */
    private List<KcNodeRuntimeInfo> conditions;
}
