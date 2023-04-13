package com.iscas.base.biz.aop.norepeat.submit;

import java.lang.annotation.*;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/5/27 21:13
 * @since jdk1.8
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
@Documented
public @interface NoRepeatSubmit {

}
