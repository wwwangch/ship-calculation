package com.iscas.common.k8s.tools.model.secret;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * K8S的secret
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/2/18 11:03
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class KcSecret {
    /**命名空间*/
    protected String namespace;

    /**名称*/
    protected String name;

    /**类型,支持dockerConfig|opaque|serviceAccount|tls|bootstrap  */
    protected String type;

    /**可不可以编辑(不需要显示)*/
    protected boolean canEdit = true;

    /**创建时间*/
    protected Date createTime;
}
