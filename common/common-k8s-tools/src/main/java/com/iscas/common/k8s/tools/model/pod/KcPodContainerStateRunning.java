package com.iscas.common.k8s.tools.model.pod;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

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
public class KcPodContainerStateRunning {

    private Date startedAt;

}
