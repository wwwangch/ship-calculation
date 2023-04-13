package com.iscas.common.k8s.tools.model.secret;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/2/18 11:07
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class DockerKcSecret extends KcSecret {
    /**docker server*/
    private String server;

    /**docker username*/
    private String username;

    /**docker password*/
    private String password;
}
