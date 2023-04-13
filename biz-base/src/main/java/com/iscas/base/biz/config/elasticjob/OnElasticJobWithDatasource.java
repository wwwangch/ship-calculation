package com.iscas.base.biz.config.elasticjob;

import com.iscas.base.biz.aop.enable.EnableElasticJob;
import com.iscas.common.tools.core.reflect.ReflectUtils;
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
 * 升级至3.0暂时不支持配置
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/4/1 9:41
 * @since jdk1.8
 */
@Order(Ordered.HIGHEST_PRECEDENCE + 21)
@Deprecated
public class OnElasticJobWithDatasource extends SpringBootCondition {
    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        ConditionMessage.Builder message = ConditionMessage.forCondition("");
        Map<String, Object> beansWithAnnotation = Objects.requireNonNull(context.getBeanFactory()).getBeansWithAnnotation(EnableElasticJob.class);
        boolean match = MapUtils.isNotEmpty(beansWithAnnotation);
        if (match) {
            for (Map.Entry<String, Object> objectEntry : beansWithAnnotation.entrySet()) {
                EnableElasticJob annotation = ReflectUtils.getAnnotationCurrent(objectEntry.getValue().getClass(), EnableElasticJob.class);
                assert annotation != null;
                return annotation.withDatasource() ? ConditionOutcome.match(message.foundExactly("EnableElaticJobDatasource"))
                        : ConditionOutcome.noMatch(message.because("not EnableElaticJobDatasource"));
            }
        }
        return ConditionOutcome.noMatch(message.because("not EnableElaticJob"));
    }
}
