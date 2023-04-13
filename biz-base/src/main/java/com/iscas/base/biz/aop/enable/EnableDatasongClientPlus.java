package com.iscas.base.biz.aop.enable;

import com.iscas.base.biz.config.datasongplus.DatasongClientPlusConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 *
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/3/5 14:16
 * @since jdk1.8
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(DatasongClientPlusConfig.class)
public @interface EnableDatasongClientPlus {
}
