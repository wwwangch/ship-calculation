package com.iscas.base.biz.aop.enable;

import com.iscas.base.biz.config.shedlock.ShedLockConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 *
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/05/20 14:16
 * @since jdk1.8
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ShedLockConfig.class)
public @interface EnableShedLock {
}
