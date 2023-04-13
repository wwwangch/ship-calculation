package com.iscas.biz.mp.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.iscas.biz.mp.config.db.DynamicDataSource;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 动态注册数据源
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/11/16 10:09
 * @since jdk1.8
 */
@Component
public class DatasourceRegistryUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    /**
     * 注册一个新的数据源
     *
     * @param dbName     数据源名称
     * @param dataSource java.sql.DataSource
     * @since jdk11
     */
    public static void registerDatasource(String dbName, DataSource dataSource) {
        DynamicDataSource dynamicDataSource = getDynamicDataSource();
        Map<Object, DataSource> resolvedDataSources = dynamicDataSource.getResolvedDataSources();
        Map<Object, Object> copyDs = new LinkedHashMap<>(resolvedDataSources);
        copyDs.put(dbName, dataSource);
        dynamicDataSource.setTargetDataSources(copyDs);
        dynamicDataSource.afterPropertiesSet();
    }

    /**
     * 移除一个数据源
     *
     * @param dbName 数据源名称
     * @since jdk11
     */
    public static void removeDatasource(String dbName) {
        DynamicDataSource dynamicDataSource = getDynamicDataSource();
        Map<Object, DataSource> resolvedDataSources = dynamicDataSource.getResolvedDataSources();
        Map<Object, Object> copyDs = new LinkedHashMap<>(resolvedDataSources);

        // 如果删除的是第一个数据源，重新设置默认数据源
        boolean resetDefaultDs = false;
        if (Objects.equals(dbName, copyDs.entrySet().iterator().next().getKey())) {
            resetDefaultDs = true;
        }
        DataSource rmDs = (DataSource) copyDs.remove(dbName);
        if (rmDs instanceof DruidDataSource) {
            // 如果是DruidDataSource，销毁连接
            ((DruidDataSource) rmDs).close();
        }

        dynamicDataSource.setTargetDataSources(copyDs);
        if (resetDefaultDs && copyDs.size() > 0) {
            dynamicDataSource.setDefaultTargetDataSource(copyDs.entrySet().iterator().next().getValue());
        }
        dynamicDataSource.afterPropertiesSet();
    }

    public static Connection getConnection() throws SQLException {
        return getDynamicDataSource().getConnection();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        DatasourceRegistryUtils.applicationContext = applicationContext;
    }


    private static DynamicDataSource getDynamicDataSource() {
        return (DynamicDataSource) applicationContext.getBean("dynamicDatasource");
    }
}
