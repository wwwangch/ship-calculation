package com.iscas.common.k8s.tools.model.pod;

import com.iscas.common.k8s.tools.model.KcResource;
import com.iscas.common.k8s.tools.model.env.KcEnv;
import com.iscas.common.k8s.tools.model.health.KcLivenessProbe;
import com.iscas.common.k8s.tools.model.health.KcReadinessProbe;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * container
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/31 18:03
 * @since jdk1.8
 */
@SuppressWarnings("DanglingJavadoc")
@Data
@Accessors(chain = true)
public class KcPodContainer {

    /**镜像*/
    private String image;

    /**拉取策略*/
    private String imagePullPolicy;

    /**
     * 存活检查
     * */
    private KcLivenessProbe livenessProbe;

    /**
     * 名称
     * */
    private String name;

    /**
     * 就绪检查
     * */
    private KcReadinessProbe readinessProbe;

    /**端口*/
    private List<KcPodContainerPort> containerPorts;

    /**
     * terminationMessagePath
     * */
    private String terminationMessagePath;

    /**
     * terminationMessagePolicy
     * */
    private String terminationMessagePolicy;


    /**
     * 存储 TODO
     * */
    private String volumeDevices;

    /**
     * containerId
     * */
    private String containerId;

    /**
     * imageId
     * */
    private String imageId;

    /**
     * ready
     * */
    private boolean ready;

    /**
     * 重启次数
     * */
    private int restartCount;

    private KcPodContainerStateRunning lastStateRunning;
    private KcPodContainerStateTerminated lastStateTerminated;
    private KcPodContainerStateWaiting lastStateWaiting;
    private KcPodContainerStateRunning stateRunning;
    private KcPodContainerStateTerminated stateTerminated;
    private KcPodContainerStateWaiting stateWaiting;

    /**
     * CPU
     * */
//    private double cpu = 0;

    /**
     * 已使用的CPU资源
     * */
    private double usedCpu = 0;

    /**
     * 内存
     * */
//    private long memory = 0;

    /**
     * 已使用的内存资源
     * */
    private long usedMemory = 0;

    /**
     * 短暂存储
     * */
//    private long transientStorage = 0;

    /**
     * 已使用的本地存储资源
     * */
    private long usedTransientStorage = 0;

    /**
     * 资源占用情况
     * */
    private KcResource resource;

    /**
     * 环境变量
     * */
    private LinkedHashMap<String, String> envs;

    /**
     * 环境变量-新，envs属性弃用
     * */
    private List<KcEnv> envVar;

    /**挂载点*/
    private List<KcPodContainerVoMount> volumeMounts;
}
