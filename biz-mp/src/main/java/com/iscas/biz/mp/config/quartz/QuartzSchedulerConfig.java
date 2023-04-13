package com.iscas.biz.mp.config.quartz;

import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * quartz配置
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/3/13 20:37
 * @since jdk1.8
 */
@SuppressWarnings("unused")
@EnableScheduling
public class QuartzSchedulerConfig {

    /**
     * 配置文件路径
     */
    private static final String QUARTZ_CONFIG = "/newframe-quartz.properties";

    /**
     * 配置任务工厂实例
     *
     * @param applicationContext spring上下文实例
     * @return JobFactory
     */
    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {

        // 采用自定义任务工厂 整合spring实例来完成构建任务 see {@link AutowiringSpringBeanJobFactory}
        JobFactory jobFactory = new JobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }


    /**
     * 配置任务调度器
     * 使用项目数据源作为quartz数据源
     *
     * @param jobFactory 自定义配置任务工厂
     * @param dataSource 数据源实例
     * @return SchedulerFactoryBean
     * @throws IOException IO异常
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory, DataSource dataSource, ApplicationContext applicationContext) throws IOException {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        // 将spring管理job自定义工厂交由调度器维护
        schedulerFactoryBean.setJobFactory(jobFactory);
        // 设置覆盖已存在的任务
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        // 项目启动完成后，等待10秒后开始执行调度器初始化
        schedulerFactoryBean.setStartupDelay(10);
        // 设置调度器自动运行
        schedulerFactoryBean.setAutoStartup(true);
        // 设置数据源，使用与项目统一数据源
        AbstractRoutingDataSource abstractRoutingDataSource = (AbstractRoutingDataSource) dataSource;
        DataSource quartzDatasource = abstractRoutingDataSource.getResolvedDefaultDataSource();
        // 将默认数据源注册到spring中
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) autowireCapableBeanFactory;
        assert quartzDatasource != null;
        beanFactory.registerSingleton("quartzDatasource", quartzDatasource);


        // 设置数据源
        schedulerFactoryBean.setDataSource(quartzDatasource);
        // 设置上下文spring bean name
        schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContext");
        // 设置配置信息
        schedulerFactoryBean.setQuartzProperties(quartzProperties());
        return schedulerFactoryBean;
    }

    /**
     * 从dmo-quartz.properties文件中读取Quartz配置属性
     *
     * @return Properties
     * @throws IOException IO异常
     */
    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource(QUARTZ_CONFIG));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    /**
     * 初始化监听器
     *
     * @return QuartzInitializerListener
     */
    @Bean
    public QuartzInitializerListener executorListener() {
        return new QuartzInitializerListener();
    }


}
