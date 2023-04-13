package com.iscas.base.biz.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 启动启动后监听
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/1/24 8:48
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "CommentedOutCode"})
@Slf4j
@Component
public class MyApplicationStartedListener implements ApplicationListener<ApplicationStartedEvent> {
    @Autowired
    private ListenerStartedHelper listenerStartedHelper;
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        ConfigurableApplicationContext applicationContext = event.getApplicationContext();
        log.info("================进入服务启动后监听================");
        listenerStartedHelper.startFilters(applicationContext);
        //测试动态定时任务
//        testTask(applicationContext);

    }

//    private void testTask(ConfigurableApplicationContext applicationContext) {
//        CronTaskRegister cronTaskRegister = applicationContext.getBean(CronTaskRegister.class);
//        SchedulingRunnable task = new SchedulingRunnable("TestTask", "test", null);
//        //每30S执行一次任务
//        cronTaskRegister.addCronTask(task, "0/10 * * * * ?");
//    }

}

