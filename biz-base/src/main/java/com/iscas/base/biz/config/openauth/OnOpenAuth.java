package com.iscas.base.biz.config.openauth;

import com.iscas.base.biz.aop.enable.EnableOpenAuthClient;
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
 * @date 2021/12/22 14:57
 * @since jdk1.8
 */
public class OnOpenAuth extends SpringBootCondition {
    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        ConditionMessage.Builder message = ConditionMessage.forCondition("");
        Map<String, Object> enableSocketioMap = Objects.requireNonNull(context.getBeanFactory()).getBeansWithAnnotation(EnableOpenAuthClient.class);
        return MapUtils.isNotEmpty(enableSocketioMap) ? ConditionOutcome.match(message.foundExactly("EnableOpenAuthClient")) :
                ConditionOutcome.noMatch(message.because("not EnableOpenAuthClient"));
    }
}
