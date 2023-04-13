package com.iscas.base.biz.config.norepeat.submit;

import com.iscas.base.biz.aop.enable.EnableNoRepeatSubmit;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.NonNull;


/**
 *
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/5/27 21:43
 * @since jdk1.8
 */
public class NoRepeatSubmitLockTypeImportSelector implements ImportSelector {

    @Override
    @NonNull
    public String[] selectImports(@NonNull AnnotationMetadata importingClassMetadata) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableNoRepeatSubmit.class.getName(), false));
        assert attributes != null;
        NoRepeatSubmitLockType lockType = attributes.getEnum("lockType");
        return switch (lockType) {
            case JVM -> new String[] {NoRepeatSubmitJVMConfig.class.getName()};
            case REDIS -> new String[] {NoRepeatSubmitRedisConfig.class.getName()};
            default -> new String[] {NoRepeatSubmitNoneConfig.class.getName()};
        };
    }
}