package com.iscas.biz.config.mp;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.MybatisMapWrapperFactory;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.mp.config.db.SqlSessionFactoryCustomizer;
import com.iscas.common.tools.core.reflect.ReflectUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.Configuration;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * 扩展mybatis-plus的配置
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/1/3 19:58
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "rawtypes"})
@Slf4j
@Component
@ConditionalOnMybatis
public class MySqlSessionFactoryCustomizer implements SqlSessionFactoryCustomizer {
    @Override
    public void customize(Configuration configuration, FactoryBean sessionFactory) {
        if (sessionFactory instanceof MybatisSqlSessionFactoryBean mssfb) {
            try {
                GlobalConfig globalConfig = (GlobalConfig) ReflectUtils.getValue(mssfb, MybatisSqlSessionFactoryBean.class, "globalConfig");
                globalConfig.setMetaObjectHandler(new MyMetaObjectHandler());
            } catch (IllegalAccessException e) {
                log.error("MySqlSessionFactoryCustomizer 出错", e);
            }
            // 返回Map时也能返回驼峰的值
//            configuration.setObjectWrapperFactory(new MybatisMapWrapperFactory());
        }
    }
}
