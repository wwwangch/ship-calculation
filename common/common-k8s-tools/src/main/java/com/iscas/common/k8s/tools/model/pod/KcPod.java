package com.iscas.common.k8s.tools.model.pod;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * Pod
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/31 17:53
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class KcPod {

    /**
     * 基本信息
     * */
    private KcPodBaseInfo baseInfo;

    /**
     * container
     * */
    private List<KcPodContainer> containers = new ArrayList<>();

    /**
     * container
     * */
    private List<KcPodContainer> initContainers = new ArrayList<>();

    /**
     * conditions
     * */
    private List<KcPodCondition> kcPodConditions = new ArrayList<>();



}
