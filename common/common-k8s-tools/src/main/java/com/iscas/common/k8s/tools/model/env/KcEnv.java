package com.iscas.common.k8s.tools.model.env;

import lombok.Data;

/**
 * 环境变量
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/10/13 14:57
 * @since jdk1.8
 */
@Data
public class KcEnv {
    /**类型,当前支持两种类型：键值对和ConfigMap*/
    private String type;

}
