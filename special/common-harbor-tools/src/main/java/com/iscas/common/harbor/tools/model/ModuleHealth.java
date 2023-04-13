package com.iscas.common.harbor.tools.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Harbor健康状况
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/8 18:52
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class ModuleHealth {
    /**组件名称*/
    private String name;
    /**组件名称*/
    private String status;
}
