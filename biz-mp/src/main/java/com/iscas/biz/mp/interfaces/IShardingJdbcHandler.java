package com.iscas.biz.mp.interfaces;

import com.iscas.biz.mp.config.db.MultiDatasourceAspectJExpressionPointcutAdvisor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/5/28 21:56
 * @since jdk1.8
 */
public interface IShardingJdbcHandler {
    /**
     * 初始化
     *
     * @return java.util.Map<java.lang.String, javax.sql.DataSource>
     * @date 2022/4/22
     * @since jdk11
     */
    Map<String, DataSource> initShardingDatasource();

    /**
     * 注册切面
     *
     * @param registry                                        BeanDefinitionRegistry
     * @param multiDatasourceAspectJExpressionPointcutAdvisor advisor
     * @date 2022/4/22
     * @since jdk11
     */
    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    void registShardingAspect(BeanDefinitionRegistry registry, MultiDatasourceAspectJExpressionPointcutAdvisor multiDatasourceAspectJExpressionPointcutAdvisor);
}
