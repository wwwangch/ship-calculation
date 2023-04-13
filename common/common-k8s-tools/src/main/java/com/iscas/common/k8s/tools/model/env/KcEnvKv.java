package com.iscas.common.k8s.tools.model.env;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 键值对类型的的环境变量
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/10/13 15:03
 * @since jdk1.8
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class KcEnvKv extends KcEnv {

    /**键值对类型的子类型,当前支持值、ConfigMap*/
    private String subType;

    /**key*/
    private String key;
}
