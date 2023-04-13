package com.iscas.common.k8s.tools.model.volume;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * 存储卷声明
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/3/22 19:19
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class KcVolumeClaimTemplate {
    /**名称*/
    private String name;

    /**存储类*/
    private String storageClass;

    /**读写模式*/
    private List<String> accessModes = new ArrayList<>();

    /**资源大小(GB)*/
    private double storage;

}
