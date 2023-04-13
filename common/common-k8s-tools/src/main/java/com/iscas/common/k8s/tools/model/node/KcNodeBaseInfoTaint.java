package com.iscas.common.k8s.tools.model.node;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 节点基本信息污点
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/2 16:43
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class KcNodeBaseInfoTaint {

    private String key;

    private String value;


    /**
     * 效果 三种 NoSchedule; PreferNoSchedule; NoExecute
     * */
    private String effect;

}
