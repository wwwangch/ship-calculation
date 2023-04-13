package com.iscas.common.k8s.tools.model.service;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * service信息
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/30 10:07
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class KcService {

    /**
     * API VERSION
     * */
    private String apiVersion = "v1";

    /**
     * 名称
     * */
    private String name;

    /**
     * 运行时间
     * */
    private String runTimeStr;

    /**
     * 命名空间
     * */
    private String namespace;

    /**
     * ClusterIP
     * */
    private String clusterIp;

    /**
     * 类型 (NodePort, ClusterIP, LoadBalancer, ExternalName)
     * */
    private String type = "ClusterIP";

    /**
     * 端口
     * */
    private Set<KcServicePort> ports = new HashSet<>();

    /**
     * selector
     * */
    private List<String[]> selectors = new ArrayList<>();

}
