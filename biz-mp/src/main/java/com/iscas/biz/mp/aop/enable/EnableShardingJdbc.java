package com.iscas.biz.mp.aop.enable;

import java.lang.annotation.*;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/05/27 9:42
 * @since jdk1.8
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Import(value={ShardingJdbcConfiguration.class})
public @interface EnableShardingJdbc {
}
