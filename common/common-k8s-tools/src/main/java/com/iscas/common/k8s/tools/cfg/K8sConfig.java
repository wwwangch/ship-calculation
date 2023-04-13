package com.iscas.common.k8s.tools.cfg;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * K8S访问使用的一些连接信息
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/2 13:50
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class K8sConfig {

    /**
     * ca证书的位置
     * */
    private String caPath ;

    /**
     * token
     * */
    private String token;

    /**
     * apiserver 的路径
     * */
    private String apiServerPath;

    /**
     * 版本号，默认为v1
     * */
    private String apiVersion = "v1";



}
