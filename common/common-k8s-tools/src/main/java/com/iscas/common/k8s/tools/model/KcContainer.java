package com.iscas.common.k8s.tools.model;

import com.iscas.common.k8s.tools.model.env.KcEnv;
import com.iscas.common.k8s.tools.model.health.KcLivenessProbe;
import com.iscas.common.k8s.tools.model.health.KcReadinessProbe;
import com.iscas.common.k8s.tools.model.pod.KcPodContainerVoMount;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 工作容器
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/9 14:31
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class KcContainer {

    /**
    * 容器名称
    * */
    private String name;

    /**
     * 镜像
     * */
    private String image;

    /**
     * 抓取策略 Always / IfNotPresent / Never
     * */
    private String imagePullPolicy = "IfNotPresent";

    /**
     * 启动容器的运行命令，将覆盖容器中的Entrypoint,对应Dockefile中的ENTRYPOINT
     * */
    private List<String> commands;

    /**
     * 启动容器的命令参数，对应Dockerfile中CMD参数
     * */
    private List<String> args;

    /**
     * 环境变量
     * */
    private LinkedHashMap<String, String> env;


    /**环境变量-支持更多种类型的配置，弃用上面的env*/
    private List<KcEnv> envVar;

    /**
     * 挂载点
     * */
    private List<KcPodContainerVoMount> volumeMounts;

    /**
     * 就绪检查
     * */
    private KcReadinessProbe readinessProbe;

    /**
     * 存活检查
     * */
    private KcLivenessProbe livenessProbe;

    /**
     * 资源设置
     * */
    private KcResource resource;

    /**
     * 端口
     * */
    private List<KcContainerPort> ports;

}
