package com.iscas.common.k8s.tools.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/9 17:48
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class KcContainerPort {
    private String name;
    private Integer containerPort;
    private Integer hostPort;
    private String protocol;
    private String hostIp;
}
