package com.iscas.common.k8s.tools.model.node;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * 节点信息
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/2 16:05
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class KcNode {

    /**
     * 节点名称
     * */
    private String name;

    /**
     * API VERSION
     * */
    private String apiVersion = "v1";

    /**
     * 节点IP
     * */
    private String address;

    /**
     * 节点状态
     * */
    private String status;

    /**
     * 节点运行时间
     * */
    private String runTimeStr;

    /**
     * 基本信息
     * */
    private KcNodeBaseInfo baseInfo;

    /**
     * 容器组（pod）信息，
     * */
    private List<KcNodePod> pods = new ArrayList<>();

    /**
     * 镜像信息
     * */
    private List<KcNodeImage> images;

    /**
     * 是否为master节点
     * */
    private boolean master = false;

}
