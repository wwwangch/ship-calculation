package com.iscas.base.biz.config.datasongplus;

import com.iscas.base.biz.aop.enable.EnableDatasongClientPlus;
import org.apache.commons.collections4.MapUtils;
import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;
import java.util.Objects;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/4/11 13:39
 * @since jdk1.8
 */
public class OnDatasongPlus extends SpringBootCondition {
    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        ConditionMessage.Builder message = ConditionMessage.forCondition("");
        Map<String, Object> enableDatasongClientPluseMap = Objects.requireNonNull(context.getBeanFactory()).getBeansWithAnnotation(EnableDatasongClientPlus.class);
        return MapUtils.isNotEmpty(enableDatasongClientPluseMap) ? ConditionOutcome.match(message.foundExactly("EnableDatasongPlus")) :
                ConditionOutcome.noMatch(message.because("not EnableDatasongPlus"));
    }
}
