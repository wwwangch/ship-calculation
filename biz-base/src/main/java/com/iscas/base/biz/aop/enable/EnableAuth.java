package com.iscas.base.biz.aop.enable;

import com.iscas.base.biz.config.common.LoginConfig;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启用户认证权限校验注解
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/18 13:37
 * @since jdk1.8
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(LoginConfig.class)
public @interface EnableAuth {
    AdviceMode mode() default AdviceMode.PROXY;
}
