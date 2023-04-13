package com.iscas.common.k8s.tools.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 挂载点
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/9 14:37
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class KcVolumeMounts {

    /**
     * 名称
     * */
    private String name;

    /**
     * 挂载在容器内的目录
     * */
    private String mountPath;

    /**
     * 子目录
     * */
    private String subPath;

    /**
     * 读写模式
     * */
    private boolean readOnly = false;

}
