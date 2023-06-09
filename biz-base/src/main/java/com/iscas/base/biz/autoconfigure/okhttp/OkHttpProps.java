package com.iscas.base.biz.autoconfigure.okhttp;//package com.iscas.cmi.config.okhttp;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Min;

/**
 * 自动配置属性
 *
 * @author zhuquanwen
 */
@ConfigurationProperties(prefix = "okhttp")
@Data
@Validated
public class OkHttpProps {
    /**
     * 读取超时时间毫秒
     */
    @Min(2000)
    private int readTimeout = 10000;

    /**
     * 写数据超时时间毫秒
     */
    @Min(2000)
    private int writeTimeout = 10000;

    /**
     * 连接超时时间毫秒
     */
    @Min(2000)
    private int connectTimeout = 10000;

    /**
     * 最大空闲数目
     */
    private int maxIdleConnection = 15;

    /**
     * keep alive保持时间 分钟
     */
    private long keepAliveDuration = 5;

    /**
     * 每个域名下最多允许的请求
     */
    private int maxRequests = 100;

    /**
     * 最大允许请求数
     */
    private int maxRequestsPerHost = 100;

    /**
     * 拦截器类的全限定名
     */
    private String interceptorClasses;


}
