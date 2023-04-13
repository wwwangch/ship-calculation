package com.iscas.common.k8s.tools.model.health;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 存活检测
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/9 15:04
 * @since jdk1.8
 */
@SuppressWarnings("Lombok")
@Data
@Accessors(chain = true)
public class KcLivenessProbe extends KcProbe {

}
