package com.iscas.base.biz.autoconfigure.cache;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.Map;

/**
 * 缓存配置
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/2/22 9:11
 */
@Data
@ConfigurationProperties(prefix = "spring.cache")
public class CacheSpec {
    private Map<String, Spec> specs;

    @Data
    public static class Spec {
        private Duration expireTime;

        private Integer maxSize;
    }
}
