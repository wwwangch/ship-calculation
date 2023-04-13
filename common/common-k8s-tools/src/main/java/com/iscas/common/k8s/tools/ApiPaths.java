package com.iscas.common.k8s.tools;

/**
 *
 * 访问K8s的API 路径
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/23 15:05
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public interface ApiPaths {
    /**通过metrics-server获取节点资源能使用情况的API*/
    String API_PATH_METRICS_NODES = "/apis/metrics.k8s.io/v1beta1/nodes";

    /**通过metrics-server获取POD资源使用情况的API*/
    String API_PATH_METRICS_PODS = "/apis/metrics.k8s.io/v1beta1/namespaces/{namespace}/pods";

}
