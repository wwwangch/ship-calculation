package com.iscas.common.k8s.tools.model.health;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/1 11:36
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class KcProbe {
    /**
     * 就绪检测类型 HTTP / TCP / COMMAND
     * */
    private String type;

    /**
     * TCP参数,类型为TCP才会有值
     * */
    private KcHealthTcpParam healthTcpParam;

    /**
     * HTTP参数，类型为HTTP才会有值
     * */
    private KcHealthHttpParam healthHttpParam;

    /**
     * COMMAND参数，类型为command才会有值
     * */
    private KcHealthCommandParam healthCommandParam;

}
