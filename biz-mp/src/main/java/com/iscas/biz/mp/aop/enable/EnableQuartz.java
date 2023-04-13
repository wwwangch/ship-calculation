package com.iscas.biz.mp.aop.enable;

import com.iscas.biz.mp.config.quartz.QuartzSchedulerConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/3/26 9:26
 * @since jdk1.8
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(QuartzSchedulerConfig.class)
public @interface EnableQuartz {
}
