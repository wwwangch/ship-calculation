package com.iscas.base.biz.aop.enable;

import com.iscas.base.biz.config.health.HealthCheckConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 是否启动健康检测
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/10 15:14
 * @since jdk1.8
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(HealthCheckConfig.class)
public @interface EnableHealthCheck {
}
