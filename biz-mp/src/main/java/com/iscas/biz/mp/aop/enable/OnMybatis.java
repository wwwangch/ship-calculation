package com.iscas.biz.mp.aop.enable;

import org.apache.commons.collections4.MapUtils;
import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;
import java.util.Objects;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/4/15 10:35
 * @since jdk1.8
 */
@Order(Ordered.HIGHEST_PRECEDENCE + 20)
public class OnMybatis extends SpringBootCondition {
    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {

        ConditionMessage.Builder message = ConditionMessage.forCondition("");
        Map<String, Object> beansWithAnnotation = Objects.requireNonNull(context.getBeanFactory()).getBeansWithAnnotation(EnableMybatis.class);
        boolean match = MapUtils.isNotEmpty(beansWithAnnotation);
        return match ? ConditionOutcome.match(message.foundExactly("EnableMybatis")) :
                ConditionOutcome.noMatch(message.because("not EnableMybatis"));
    }
}