package com.iscas.common.k8s.tools.model.autoscale;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 自动扩容
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/22 10:33
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode
public class KcAutoscale {
    /**
     * 命名空间
     * */
    private String namespace;

    /**
     * 名称，deploymentName
     * */
    private String name;

    /**
     * 最小副本数
     * */
    private int minReplicas;

    /**
     * 扩容到的最大副本数
     * */
    private int maxReplicas;

    /**
     * 扩容的CPU资源阈值
     * */
    private KcAutoScaleResource cpuResource;

    /**
     * 扩容的内存资源阈值
     * */
    private KcAutoScaleResource memoryResource;

    /**
     * 当前副本数 只展示
     * */
    private Integer currentReplicas;

    /**
     * 上一次扩容的时间
     * */
    private Date lastScaleTime;
}
