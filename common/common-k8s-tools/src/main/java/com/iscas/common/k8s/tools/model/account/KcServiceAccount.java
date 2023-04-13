package com.iscas.common.k8s.tools.model.account;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/1 19:24
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class KcServiceAccount {
    /**名称*/
    private String username;

    /**命名空间*/
    private Set<String> namespace = new HashSet<>();
}
