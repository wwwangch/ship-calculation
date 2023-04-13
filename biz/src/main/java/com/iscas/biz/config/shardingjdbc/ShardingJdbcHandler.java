package com.iscas.biz.config.shardingjdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.mp.config.db.DruidConfiguration;
import com.iscas.biz.mp.config.db.MultiDatasourceAspectJExpressionPointcutAdvisor;
import com.iscas.biz.mp.interfaces.IShardingJdbcHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.jetbrains.annotations.NotNull;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 *
 * <p>sharding-jdbc 相关配置，由于要配置各种策略，做成动态配置的话</p>
 * <p>侵入性太强，提取到biz模块让用户自行配置</p>
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/5/28 22:00
 * @since jdk1.8
 */
@SuppressWarnings("unused")
@Slf4j
@Component
@ConditionalOnMybatis
public class ShardingJdbcHandler implements IShardingJdbcHandler, EnvironmentAware {
    private Environment environment;

    /**
     * 配置shardingjdbc的datasource，支持配置多个shardingjdbc,返回为dbName为key,datasource为value
     * 这里默认配置了一个
     * @since jdk1.8
     * @date 2021/5/28
     * @return java.util.Map<java.lang.String,javax.sql.DataSource>
     */
    @Override
    public Map<String, DataSource> initShardingDatasource() {
        Map<String, DataSource> result = new HashMap<>(16);
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>(16);


        DruidDataSource dataSource0 = DruidConfiguration.doInitOneDatasource("spring.datasource.druid.sharding.ds0.", "ds0", new DruidDataSource(), environment);
        DruidDataSource dataSource1 = DruidConfiguration.doInitOneDatasource("spring.datasource.druid.sharding.ds1.", "ds1", new DruidDataSource(), environment);
        dataSourceMap.put("ds0", dataSource0);
        dataSourceMap.put("ds1", dataSource1);

        // 配置Order表规则
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration("t_order",
                "ds${0..1}.t_order${0..1}");

        // 配置分库 + 分表策略
        orderTableRuleConfig.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "ds${user_id % 2}"));
        orderTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("order_id", "t_order${order_id % 2}"));

        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);

        // 获取数据源对象
        DataSource dataSource;
        try {
            dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new Properties());
            result.put("ds0_ds1", dataSource);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;

    }

    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    @Override
    public void registShardingAspect(BeanDefinitionRegistry registry, MultiDatasourceAspectJExpressionPointcutAdvisor multiDatasourceAspectJExpressionPointcutAdvisor) {
        String shardingNames = environment.getProperty("spring.datasource.sharding.names");
        assert shardingNames != null;
        String shardingPointcutNames = String.join(".", shardingNames.split(","));
        String key = "spring.datasource.sharding." + shardingPointcutNames + ".pointcut";
        String shardingDbName = String.join("_", shardingNames.split(","));
        String value = environment.getProperty(key);
        if (StringUtils.isNotBlank(value)) {
            AbstractBeanDefinition definition = BeanDefinitionBuilder
                    .genericBeanDefinition(AspectJExpressionPointcutAdvisor.class, () -> multiDatasourceAspectJExpressionPointcutAdvisor.getAspectJExpressionPointcutAdvisor(value, shardingDbName))
                    .getBeanDefinition();
            registry.registerBeanDefinition(AspectJExpressionPointcutAdvisor.class.getSimpleName() + shardingDbName, definition);
        } else {
            log.error("{}数据源没有配置切面的目录", shardingDbName);
        }
    }

    @Override
    public void setEnvironment(@NotNull Environment environment) {
        this.environment = environment;
    }
}
