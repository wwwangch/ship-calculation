package com.iscas.common.k8s.tools.model.pod;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/31 18:23
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class KcPodContainerStateWaiting {
    private String message;
    private String reason;

}
