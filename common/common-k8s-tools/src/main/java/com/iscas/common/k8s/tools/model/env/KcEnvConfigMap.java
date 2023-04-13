package com.iscas.common.k8s.tools.model.env;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ConfigMap类型的的环境变量
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/10/13 15:03
 * @since jdk1.8
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class KcEnvConfigMap extends KcEnv {

    /**configmap的名字*/
    private String configMapName;
}
