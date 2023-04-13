package com.iscas.common.k8s.tools.model.env;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 值类型的环境变量
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/10/13 15:06
 * @since jdk1.8
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class KcEnvKvV extends KcEnvKv{
    /**值*/
    private String value;
}
