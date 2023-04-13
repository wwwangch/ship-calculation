package com.iscas.biz.mp.config.db;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.FactoryBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/1/5 9:20
 * @since jdk1.8
 */
public class SqlSessionFactoryCustomizers {

    private final List<SqlSessionFactoryCustomizer<?, ?>> customizers;

    public SqlSessionFactoryCustomizers(List<? extends SqlSessionFactoryCustomizer<?, ?>> customizers) {
        this.customizers = (customizers != null) ? new ArrayList<>(customizers) : Collections.emptyList();
    }

    @SuppressWarnings("UnusedReturnValue")
    public <T extends Configuration,S extends FactoryBean<? extends SqlSessionFactory>> S customize(T configuration, S sessionFactory){
        customizers.forEach(customizer -> customizer.customize(configuration, sessionFactory));
        return sessionFactory;
    }
}
