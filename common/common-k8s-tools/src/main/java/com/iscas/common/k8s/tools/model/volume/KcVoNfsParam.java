package com.iscas.common.k8s.tools.model.volume;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/9 14:48
 * @since jdk1.8
 */
@SuppressWarnings("Lombok")
@Data
@Accessors(chain = true)
public class KcVoNfsParam extends KcVolumeParam{

    /**
     * nfs-server地址
     * */
    private String server;

    /**
     * nfs path
     * */
    private String path;

    /**
     * 是否只读
     * */
    private boolean readOnly = false;

}
