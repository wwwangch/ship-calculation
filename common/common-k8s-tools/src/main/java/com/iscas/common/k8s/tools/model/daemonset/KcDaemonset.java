package com.iscas.common.k8s.tools.model.daemonset;

import com.iscas.common.k8s.tools.model.deployment.KcDeployment;
import io.fabric8.kubernetes.api.model.apps.DaemonSet;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 守护服务
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/10/27 15:34
 * @since jdk1.8
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class KcDaemonset extends KcDeployment {
    private DaemonSet daemonsetItem;
}
