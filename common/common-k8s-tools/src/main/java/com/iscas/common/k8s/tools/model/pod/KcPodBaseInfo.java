package com.iscas.common.k8s.tools.model.pod;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * pod基本信息
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/31 16:27
 * @since jdk1.8
 */
@SuppressWarnings("DanglingJavadoc")
@Data
@Accessors(chain = true)
public class KcPodBaseInfo {

    /**
     * API VERSION
     * */
    private String apiVersion = "v1";

    /**
     * 名称
     * */
    private String name;

    /**
     * 创建时间
     * */
    private Date createTime;

    /**
     * 运行时间
     * */
    private String runTimeStr;

    /**
     * 命名空间
     * */
    private String namespace;

    /**
     * labels
     * */
    private List<String[]> labels = new ArrayList<>();

    /**
     * dnsPolicy
     * */
    private String dnsPolicy;

    /**
     * enableServiceLinks
     * */
    private boolean enableServiceLinks;


    /**
     * 所在节点
     * */
    private String nodeName;

    /**
     * 重启策略
     * */
    private String restartPolicy;

    /**
     * 用户
     * */
    private String serviceAccount;


    /**
     * terminationGracePeriodSeconds
     * */
    private Long terminationGracePeriodSeconds;

    /**
     * 存储 TODO
     * */
    private String volumes;


    /**
     * 宿主IP
     * */
    private String hostIp;

    /**
     * phase
     * */
    private String phase;

    /**
     * PODip
     * */
    private String podIp;

    /**
     * qosCLASS
     * */
    private String qosClass;

    /**
     * 启动时间
     * */
    private Date startTime;

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
     * 镜像拉取秘钥
     * */
    private String imagePullSecrets;

    /**
     * 所属工作负载类型
     * */
    private String ownerReferenceKind;

    /**
     * 所属工作负载名称
     * */
    private String ownerReferenceName;

}
