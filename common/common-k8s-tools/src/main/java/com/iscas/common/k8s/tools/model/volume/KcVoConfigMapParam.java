package com.iscas.common.k8s.tools.model.volume;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

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
public class KcVoConfigMapParam extends KcVolumeParam {
    /**
     * configmap
     * */
    private String configmap;

    /**
     * key to path ,key是configmap的key，value是一个相对路径，对应的配置文件文件名
     * 可以为空
     * */
    private List<String[]> keyToPath;

}
