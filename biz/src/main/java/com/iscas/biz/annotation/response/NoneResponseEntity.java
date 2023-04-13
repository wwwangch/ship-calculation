package com.iscas.biz.annotation.response;

import java.lang.annotation.*;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/2/2 8:42
 */
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface NoneResponseEntity {
}
