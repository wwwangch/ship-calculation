package com.iscas.biz.mp.config.quartz;

import com.alibaba.druid.pool.DruidDataSource;
import org.quartz.utils.ConnectionProvider;

import
        javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 替换quartz默认使用的C3P0连接池为取spring中注册好的数据源
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/3/13 20:53
 * @since jdk1.8
 */
public class MyConnectionProvider implements ConnectionProvider {

    private DataSource datasource;

    @Override
    public Connection getConnection() throws SQLException {
        return datasource.getConnection();
    }

    @Override
    public void shutdown() {
        if (datasource instanceof DruidDataSource druidDataSource) {
            druidDataSource.close();
        }
//        else if (datasource instanceof AtomikosDataSourceBean) {
//            AtomikosDataSourceBean atomikosDataSourceBean = (AtomikosDataSourceBean) datasource;
//            atomikosDataSourceBean.close();
//        }
    }

    @Override
    public void initialize() {
        datasource = (DataSource) ApplicationUtils.getApplicationContext().getBean("quartzDatasource");
    }

    public DataSource getDatasource() {
        return datasource;
    }

    public void setDatasource(DataSource datasource) {
        this.datasource = datasource;
    }

}
