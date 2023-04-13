package com.iscas.common.k8s.tools.model.node;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 某个节点下的pod信息
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/15 12:38
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class KcNodePod {
    /**
     * 命名空间
     * */
    private String namespace;

    /**
     * 名称
     * */
    private String name;

    /**
     * 状态
     * */
    private String status;

    /**
     * 容器组IP
     * */
    private String innerIp;

    /**
     * 创建时间
     * */
    private Date createTime;

    /**
     * 运行时间
     * */
    private String runTimeStr;
}
