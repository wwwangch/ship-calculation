package com.iscas.base.biz.config.elasticjob;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * 升级至3.0后咱不支持
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/4/1 9:39
 * @since jdk1.8
 */
@Deprecated
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(OnElasticJobWithDatasource.class)
public @interface ConditionalOnElasticJobWithDatasource {

}
