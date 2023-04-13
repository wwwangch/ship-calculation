package com.iscas.biz.mp.aop.enable;

import org.apache.commons.collections4.MapUtils;
import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;
import java.util.Objects;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/3/26 10:22
 * @since jdk1.8
 */
public class OnQuartz extends SpringBootCondition {
    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        ConditionMessage.Builder message = ConditionMessage.forCondition("");
        Map<String, Object> beansWithAnnotation = Objects.requireNonNull(context.getBeanFactory()).getBeansWithAnnotation(EnableQuartz.class);
        boolean match = MapUtils.isNotEmpty(beansWithAnnotation);
        return match ? ConditionOutcome.match(message.foundExactly("EnableQuartz")) :
                ConditionOutcome.noMatch(message.because("not EnableQuartz"));
    }
}
