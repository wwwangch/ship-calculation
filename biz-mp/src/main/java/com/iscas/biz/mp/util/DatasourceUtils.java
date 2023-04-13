package com.iscas.biz.mp.util;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;

/**
 *
 * 获取datasource
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/4/17 8:54
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class DatasourceUtils {
    public DataSource getOracleDatasource(String ip, String port, String sid, String username, String pwd) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@" + ip + ":" + port + ":" + sid);
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataSource.setUsername(username);
        dataSource.setPassword(pwd);
        return dataSource;
    }
}
