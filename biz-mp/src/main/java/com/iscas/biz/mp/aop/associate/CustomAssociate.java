package com.iscas.biz.mp.aop.associate;

import java.lang.annotation.*;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/9/4 11:18
 * @since jdk1.8
 */
@SuppressWarnings("DeprecatedIsStillUsed")
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Deprecated
public @interface CustomAssociate {
    /**表1的名称(别名)*/
    String table1();
    /**表1与表2关联的字段名*/
    String table1Col();
    /**表2的名称(别名)*/
    String table2();
    /**表2与表1关联的字段名*/
    String table2Col();
    CustomAssociateType associateType();
}
