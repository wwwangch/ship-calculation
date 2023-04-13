package com.iscas.common.k8s.tools.model.deployment;

import com.iscas.common.k8s.tools.model.KcContainer;
import com.iscas.common.k8s.tools.model.KcRuntimeInfo;
import com.iscas.common.k8s.tools.model.volume.KcVolume;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * deployment
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/8 17:39
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class KcDeployment {

    /**
     * 名称
     * */
    private String name;

    /**
     * 当前副本数目
     * */
    private Integer currentRepSum;

    /**
     * 计划副本数目
     * */
    private Integer planRepSum;

    /**
     * 启动时间
     * */
    private String runtimeStr;

    /**
     * 基本信息
     * */
    private KcDepBaseInfo baseInfo;

    /**
     * 运行时信息
     * */
    private List<KcRuntimeInfo> runtimeInfos;

    /**
     * 容器信息
     * */
    private List<KcContainer> containers;

    /**
     * 初始化容器信息
     * */
    private List<KcContainer> initContainer;

    /**
     * 重启策略 Always / OnFailure / Never
     * */
    private String restartStrategy = "Always";

    /**
     * 镜像拉取的秘钥
     * */
    private String imagePullSecret;

    private Deployment deploymentItem;

    /**
     * 资源限制
     * */
//    private KcResource resources;

    /**
     * 数据卷
    * */
    private List<KcVolume> volumes = new ArrayList<>();

}
