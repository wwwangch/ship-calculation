package com.iscas.common.k8s.tools.model.service;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * service的端口
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/30 13:18
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class KcServicePort {

    /**设置的端口名称*/
    private String portName;

    /**集群内部访问端口*/
    private Integer port;

    /**容器内部端口*/
    private Integer targetPort;

    /**对外端口*/
    private Integer nodePort;

    /**协议*/
    private String protocol;
}
