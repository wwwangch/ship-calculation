package com.iscas.rule.engine.anno;

import java.lang.annotation.*;

/**
 * 自动注入参数，类似于spring的autowired
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/24 14:01
 * @since jdk1.8
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@SuppressWarnings("JavadocDeclaration")
public @interface REAutowired {

}
