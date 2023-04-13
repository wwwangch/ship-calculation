package com.iscas.common.k8s.tools.model.pod;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * containerPort
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/31 18:06
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class KcPodContainerPort {
    /**
     * 端口
     * */
    private Integer containerPort;

    /**
     * hostIp
     * */
    private String hostIp;

    /**
     * hostPort
     * */
    private String hostPort;

    /**
     * 协议
     * */
    private String protocol;




}
