package com.iscas.biz.filter;

import com.iscas.base.biz.filter.started.AbstractStartedFilter;
import com.iscas.base.biz.filter.started.StartedFilterComponent;
import com.iscas.base.biz.schedule.CronTaskRegister;
import com.iscas.base.biz.schedule.SchedulingRunnable;
import com.iscas.biz.schedule.MonitorTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/3/2 13:57
 * @since jdk1.8
 */
@SuppressWarnings("unused")
//@StartedFilterComponent(order = 3)
@Slf4j
public class MonitorTaskFilter extends AbstractStartedFilter {

    private final CronTaskRegister cronTaskRegister;
    @SuppressWarnings("FieldCanBeLocal")
    private final MonitorTask monitorTask;


    public MonitorTaskFilter(CronTaskRegister cronTaskRegister, MonitorTask monitorTask) {
        this.cronTaskRegister = cronTaskRegister;
        this.monitorTask = monitorTask;
    }

    @Override
    public void doFilterInternal(ApplicationContext applicationContext) {

        startSysMonitorTask();
        super.doFilterInternal(applicationContext);
    }

    private void startSysMonitorTask() {
        SchedulingRunnable task = new SchedulingRunnable("monitorTask", "monitor");
        //每30S执行一次任务
        cronTaskRegister.addCronTask("monitor_task", task, "0/30 * * * * ?");


    }

    @Override
    public String getName() {
        return "启动系统监控定时任务";
    }
}
