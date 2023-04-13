package com.iscas.common.k8s.tools.model.secret;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

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
public class OpaqueKcSecret extends KcSecret {
    private Map<String, String> data = new HashMap<>();
}
