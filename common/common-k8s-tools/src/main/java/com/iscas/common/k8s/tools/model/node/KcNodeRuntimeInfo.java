package com.iscas.common.k8s.tools.model.node;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 运行时信息
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/2 16:43
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class KcNodeRuntimeInfo {

    private String type;

    private String status;

    private Date lastHeartbeatTime;

    private Date lastTransitionTime;

    private String reason;

    private String message;
}
