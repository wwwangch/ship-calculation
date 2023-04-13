package com.iscas.base.biz.config.health;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

/**
 * 健康检测注册
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/10 15:13
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class HealthCheckConfig {

    @Bean
    @Lazy(value = false)
    public LivenessStateListener livenessStateListener() {
        return new LivenessStateListener();
    }

    @Bean
    @Lazy(value = false)
    public ReadinessStateListener readinessStateListener() {
        return new ReadinessStateListener();
    }

    @Bean
    @ConditionalOnMissingBean
    @Lazy(value = false)
    public IHealthCheckHandler healthCheckHelper() {
        return new DefaultHealthCheckHandler();
    }
}
