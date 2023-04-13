package com.iscas.base.biz.aop.enable;

import com.iscas.base.biz.config.log.LogRecordConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * //允许日志记录
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/8/30 18:04
 * @since jdk1.8
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(LogRecordConfig.class)
public @interface EnableLog {

}
