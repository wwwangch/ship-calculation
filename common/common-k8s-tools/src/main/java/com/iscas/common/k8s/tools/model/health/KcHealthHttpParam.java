package com.iscas.common.k8s.tools.model.health;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/9 15:07
 * @since jdk1.8
 */
@SuppressWarnings("Lombok")
@Data
@Accessors(chain = true)
public class KcHealthHttpParam extends KcHealthParam {
    /**
     * 请求协议 HTTP / HTTPS
     * */
    private String protocol;

    /**
     * 路径以 /开头
     * */
    private String path;

    /**
     * 端口
     * */
    private Integer port;

    /**
     * HTTP请求头
     * */
    private Map<String, String> headers;

    private String host;


}
