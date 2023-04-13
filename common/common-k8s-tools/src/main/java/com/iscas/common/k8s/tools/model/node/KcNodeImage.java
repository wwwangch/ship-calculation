package com.iscas.common.k8s.tools.model.node;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 节点的镜像信息
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/15 13:21
 * @since jdk1.8
 */

@Data
@Accessors(chain = true)
public class KcNodeImage {

    /**
     *  镜像names
     * */
    private List<String> names;

    /**
     * 镜像合起来的字节大小
     * */
    private Long size;
}
