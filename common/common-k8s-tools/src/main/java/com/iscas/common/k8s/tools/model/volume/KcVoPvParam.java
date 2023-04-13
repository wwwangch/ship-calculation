package com.iscas.common.k8s.tools.model.volume;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/9 14:51
 * @since jdk1.8
 */
@SuppressWarnings("Lombok")
@Data
@Accessors(chain = true)
public class KcVoPvParam extends KcVolumeParam {
    /**
     * 存储卷声明
     * */
    private String pv;
}
