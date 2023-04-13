package com.iscas.base.biz.config.shedlock;

import com.iscas.base.biz.aop.enable.EnableShedLock;
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
 * @date 2021/05/20 19:41
 * @since jdk1.8
 */
@Order(Ordered.HIGHEST_PRECEDENCE + 20)
public class OnShedLock extends SpringBootCondition {
    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        ConditionMessage.Builder message = ConditionMessage.forCondition("");
        Map<String, Object> enableShedLockMap = Objects.requireNonNull(context.getBeanFactory()).getBeansWithAnnotation(EnableShedLock.class);
        return MapUtils.isNotEmpty(enableShedLockMap) ? ConditionOutcome.match(message.foundExactly("EnableShedLock")) :
                ConditionOutcome.noMatch(message.because("not EnableShedLock"));
    }
}
