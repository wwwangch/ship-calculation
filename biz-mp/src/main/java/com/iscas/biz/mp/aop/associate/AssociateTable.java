package com.iscas.biz.mp.aop.associate;


import java.lang.annotation.*;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/9/4 11:07
 * @since jdk1.8
 */
@SuppressWarnings("DeprecatedIsStillUsed")
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Deprecated
public @interface AssociateTable {
    /**表的名称*/
    String name();
    /*关联表的别名，如果不设置，默认直接用表名*/
    String alias() default "";
}
