package com.iscas.common.k8s.tools.model.storageclass;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/3/15 15:11
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class KcStorageClass {

    /**
     * 名称
     * */
    private String name;


    /**提供者*/
    private String provisioner;

    /**回收策略*/
    private String reclaimPolicy;

    /**
     * 绑定模式
     * */
    private String volumeBindingMode;
}
