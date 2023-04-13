package com.iscas.base.biz.aop.enable;

import com.iscas.base.biz.config.elasticjob.v3.ElasticJobConfig;
import com.iscas.base.biz.config.elasticjob.v3.ElasticRegCenterConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 暂时废弃，guava冲突，暂时用不了了
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/3/26 10:14
 * @since jdk1.8
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ElasticJobConfig.class, ElasticRegCenterConfig.class})
public @interface EnableElasticJob {
    //是否使用datasource记录操作日志
    boolean withDatasource() default false;
}
