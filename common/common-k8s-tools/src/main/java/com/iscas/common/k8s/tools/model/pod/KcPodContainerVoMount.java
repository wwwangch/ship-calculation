package com.iscas.common.k8s.tools.model.pod;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 挂载点
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/3/9 15:35
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class KcPodContainerVoMount {

    /**挂载路径*/
    private String mountPath;

    /**名称*/
    private String name;

    /**是否只读*/
    private boolean readOnly = false;

    /**子路径*/
    private String subPath;

    /**环境变量，咱不支持*/
    private String subPathExpr;
}
