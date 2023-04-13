package com.iscas.biz.mp.test.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.iscas.biz.mp.config.db.DbContextHolder;
import com.iscas.biz.mp.util.DatasourceRegistryUtils;
import com.iscas.templet.common.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 测试注册一个动态数据源
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/11/16 10:15
 * @since jdk1.8
 */
@RestController
@RequestMapping("/test/dynamic-datasource/registry")
public class TestDynamicDatasourceController {

    @GetMapping
    public ResponseEntity registry() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://172.16.10.155:3306/device_test?useUnicode=true&characterEncoding=UTF-8" +
                "&serverTimezone=Asia/Shanghai&useSSL=false&autoReconnect=true&allowMultiQueries=true&rewriteBatchedStatements=true");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        DatasourceRegistryUtils.registerDatasource("test", dataSource);

        DbContextHolder.setDbType("test");
        Connection connection = DatasourceRegistryUtils.getConnection();
        System.out.println(connection);
        DbContextHolder.clearDbType();
        Connection connection2 = DatasourceRegistryUtils.getConnection();
        System.out.println(connection2);

        DatasourceRegistryUtils.removeDatasource("test");
        DatasourceRegistryUtils.removeDatasource("mysql2");
        DatasourceRegistryUtils.removeDatasource("mysql1");

        DbContextHolder.setDbType("mysql2");
        Connection connection3 = DatasourceRegistryUtils.getConnection();
        System.out.println(connection3);
        DbContextHolder.clearDbType();

        return new ResponseEntity();
    }

}
