package com.iscas.rule.engine.anno;

import java.lang.annotation.*;

/**
 * 业务操作类的注解，类似于spring的Component
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/24 14:02
 * @since jdk1.8
 */
@SuppressWarnings("JavadocDeclaration")
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface REComponent {
}
