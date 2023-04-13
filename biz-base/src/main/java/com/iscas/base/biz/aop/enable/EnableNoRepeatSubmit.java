package com.iscas.base.biz.aop.enable;

import com.iscas.base.biz.config.norepeat.submit.NoRepeatSubmitLockType;
import com.iscas.base.biz.config.norepeat.submit.NoRepeatSubmitLockTypeImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 *
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/5/27 21:35
 * @since jdk1.8
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(NoRepeatSubmitLockTypeImportSelector.class)
public @interface EnableNoRepeatSubmit {
    NoRepeatSubmitLockType lockType() default NoRepeatSubmitLockType.NONE;
}
