package com.iscas.base.biz.aop.enable;

import com.iscas.base.biz.config.openauth.OpenAuthConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启用户认证open-auth
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/12/22 13:25
 * @since jdk1.8
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(OpenAuthConfig.class)
public @interface EnableOpenAuthClient {
}
