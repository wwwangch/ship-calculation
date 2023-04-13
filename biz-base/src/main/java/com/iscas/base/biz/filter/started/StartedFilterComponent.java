package com.iscas.base.biz.filter.started;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 *
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/4/21 13:53
 * @since jdk1.8
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface StartedFilterComponent {
    int order() default Integer.MAX_VALUE;
}
