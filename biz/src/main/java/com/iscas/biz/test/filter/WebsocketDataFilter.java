package com.iscas.biz.test.filter;

import com.iscas.base.biz.filter.started.AbstractStartedFilter;
import com.iscas.base.biz.filter.started.StartedFilterComponent;
import com.iscas.base.biz.schedule.CronTaskRegister;
import com.iscas.base.biz.schedule.SchedulingRunnable;
import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/2/26 14:45
 * @since jdk1.8
 */
@StartedFilterComponent(order = 2)
@Slf4j
@ConditionalOnMybatis
public class WebsocketDataFilter extends AbstractStartedFilter {

    @Override
    public void doFilterInternal(ApplicationContext applicationContext) {
        CronTaskRegister cronTaskRegister = applicationContext.getBean(CronTaskRegister.class);
        SchedulingRunnable task = new SchedulingRunnable("TestWsTask", "test", null);
        //每30S执行一次任务
        cronTaskRegister.addCronTask("testWsTask", task, "0/30 * * * * ?");
        super.doFilterInternal(applicationContext);
    }

    @Override
    public String getName() {
        return "测试websocket过滤器";
    }
}
