package com.iscas.common.prometheus.tools.model;

import lombok.Data;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/3/30 16:04
 */
@Data
public class ConnectionCfg {

    /**
     * prometheus的url地址
     */
    private String prometheusUrl = "http://localhost:9090/";

    /**
     * alertmanager的url地址
     */
    private String alertmanagerUrl = "http://localhost:9093/";

    /**
     * 调用API的超时时间
     * */
    private long timeout = 10000;

}
