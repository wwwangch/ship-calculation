package com.iscas.common.k8s.tools.model.volume;

import lombok.Data;

/**
 * 数据卷
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/9 14:45
 * @since jdk1.8
 */
@Data
public class KcVolume {
    /**
     * 名称
     * */
    private String name;

    /**
     * 类型 NFS / pv / emptyDir / hostPath / configMap / secret
     * */
    private KcVolumeType type;

    /**
     * 参数
     * */
    private Object params;

    @SuppressWarnings("AlibabaEnumConstantsMustHaveComment")
    public enum KcVolumeType {
        NFS, pv, emptyDir, hostPath, configMap, secret
    }

}
