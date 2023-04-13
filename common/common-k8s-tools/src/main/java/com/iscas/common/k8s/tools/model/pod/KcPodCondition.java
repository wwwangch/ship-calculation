package com.iscas.common.k8s.tools.model.pod;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 *
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/31 18:16
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class KcPodCondition {
    private Date lastProbTime;
    private Date lastTransitionTime;
    private String message;
    private String reason;
    private String status;
    private String type;
}
