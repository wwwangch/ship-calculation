//package com.iscas.biz.config;
//
//import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.TransactionManager;
//import org.springframework.transaction.annotation.TransactionManagementConfigurer;
//
//import javax.sql.DataSource;
//import java.lang.reflect.Field;
//
///**
// * @author lirenshen
// * @version 1.0
// * @date 2021/2/25 18:24
// * @since jdk1.8
// */
//@Configuration
//public class TransactionManagerConfig implements TransactionManagementConfigurer {
//    @Autowired
//    private SqlSessionFactory sqlSessionFactory;
//
//    @Override
//    public TransactionManager annotationDrivenTransactionManager() {
//        MybatisSqlSessionFactoryBean sqlSessionFactory = (MybatisSqlSessionFactoryBean) this.sqlSessionFactory;
//
//        Object dataSource = null;
//        try {
//            Field dataSourceField = MybatisSqlSessionFactoryBean.class.getDeclaredField("dataSource");
//            dataSourceField.setAccessible(true);
//            dataSource = dataSourceField.get(sqlSessionFactory);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new DataSourceTransactionManager((DataSource) dataSource);
//    }
//}
