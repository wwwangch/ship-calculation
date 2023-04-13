package com.iscas.common.k8s.tools.model.health;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/9 15:07
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class KcHealthTcpParam extends KcHealthParam {

    /**
     * 端口
     * */
    private int port;


}
