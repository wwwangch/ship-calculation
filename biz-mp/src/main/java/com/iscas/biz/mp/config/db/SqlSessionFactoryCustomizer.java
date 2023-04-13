package com.iscas.biz.mp.config.db;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/1/5 9:12
 * @since jdk1.8
 * 扩展 Configuration、SqlSessionFactory
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface SqlSessionFactoryCustomizer<T extends Configuration,S extends FactoryBean<? extends SqlSessionFactory>> {

    @SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
    void customize(Configuration configuration, FactoryBean<? extends SqlSessionFactory> sessionFactory);
}
