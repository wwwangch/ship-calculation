package com.iscas.base.biz.filter;

import cn.hutool.core.collection.CollectionUtil;
import com.iscas.base.biz.aop.enable.EnableSpringBootAdminClient;
import de.codecentric.boot.admin.client.config.SpringBootAdminClientAutoConfiguration;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.autoconfigure.AutoConfigurationImportFilter;
import org.springframework.boot.autoconfigure.AutoConfigurationMetadata;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/6/3 18:09
 * @since jdk1.8
 */
@SuppressWarnings("unchecked")
public class AutoConfigurationExclusionFilter implements AutoConfigurationImportFilter, BeanFactoryAware, EnvironmentAware {
    private BeanFactory beanFactory;
    private Environment environment;
    @Override
    public boolean[] match(String[] autoConfigurationClasses, AutoConfigurationMetadata autoConfigurationMetadata) {
        boolean[] matches = new boolean[autoConfigurationClasses.length];
        for (int i = 0; i < matches.length; i++) {

            if (Objects.equals(SpringBootAdminClientAutoConfiguration.class.getName(), autoConfigurationClasses[i])) {
                //如果是Springboot-admin-client的自动配置类，查看是否允许使用
                try {
                    DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) beanFactory;
                    Map<String, Object> beansWithAnnotation = defaultListableBeanFactory.getBeansWithAnnotation(EnableSpringBootAdminClient.class);
                    matches[i] = CollectionUtil.isNotEmpty(beansWithAnnotation);
                } catch (Exception e) {
                    System.err.println("查找EnableSpringBootAdminClient注解出错");
                    matches[i] = true;
                }

            } else if (Objects.equals(JtaAutoConfiguration.class.getName(),
                    autoConfigurationClasses[i])) {
                //如果是JtaAutoConfiguration的自动配置类，查看是否允许使用
                try {
                    DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) beanFactory;
                    Class<? extends Annotation> aClass = (Class<? extends Annotation>) Class.forName("com.iscas.biz.mp.aop.enable.EnableAtomikos");
                    Map<String, Object> beansWithAnnotation = defaultListableBeanFactory.getBeansWithAnnotation(aClass);
                    matches[i] = CollectionUtil.isNotEmpty(beansWithAnnotation);
                } catch (Exception e) {
                    System.err.println("查找EnableAtomikos注解出错");
                    matches[i] = true;
                }
            } else if (Objects.equals(RedisAutoConfiguration.class.getName(), autoConfigurationClasses[i])) {
                //判断是否注册redis
                String property = environment.getProperty("spring.cache.type");
                matches[i] = Objects.equals("redis", property);
            } else if (Objects.equals("org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration", autoConfigurationClasses[i])) {
                matches[i] = false;
            } else if (Objects.equals("com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration", autoConfigurationClasses[i])) {
                matches[i] = false;
            } else {
                matches[i] = true;
            }
        }
        return matches;
    }

    @Override
    public void setBeanFactory(@NotNull BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setEnvironment(@NotNull Environment environment) {
        this.environment = environment;
    }
}
