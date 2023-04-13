package com.iscas.common.k8s.tools.model.deployment;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * deployment基本信息
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/9 9:50
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class KcDepBaseInfo {
    /**
     * 服务类型 Deployment;Pod;Service。。。。。。
     * */
    private String type = "Deployment";

    /**
     * 服务名称
     * */
    private String name;

    /**
     * 标签
     * */
    private List<String[]> labels;

    /**
     * matchLabels
     * */
    private List<String[]> matchLabels;

    /**
     *  annotations
     * */
    private List<String[]> annotations;

    /**
     * 服务描述
     * */
    private String description;

    /**
     * 当前副本数目
     * */
    private Integer currentRepSum;

    /**
     * 计划副本数目
     * */
    private Integer planRepSum;

    /**
     * 命名空间
     * */
    private String namespace;


}
