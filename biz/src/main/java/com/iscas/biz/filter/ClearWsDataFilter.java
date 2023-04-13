package com.iscas.biz.filter;

import com.iscas.base.biz.filter.started.AbstractStartedFilter;
import com.iscas.base.biz.filter.started.StartedFilterComponent;
import com.iscas.base.biz.schedule.CronTaskRegister;
import com.iscas.base.biz.schedule.SchedulingRunnable;
import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.schedule.ClearWsDataTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/2/26 15:04
 * @since jdk1.8
 */
@SuppressWarnings("unused")
//@StartedFilterComponent()
@Slf4j
@ConditionalOnMybatis
public class ClearWsDataFilter extends AbstractStartedFilter {

    private final CronTaskRegister cronTaskRegister;
    private final ClearWsDataTask clearWsDataTask;


    public ClearWsDataFilter(CronTaskRegister cronTaskRegister, ClearWsDataTask clearWsDataTask) {
        this.cronTaskRegister = cronTaskRegister;
        this.clearWsDataTask = clearWsDataTask;
    }

    @Override
    public void doFilterInternal(ApplicationContext applicationContext) {
        beginClear();
        super.doFilterInternal(applicationContext);
    }

    private void beginClear() {
        try {
            clearWsDataTask.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SchedulingRunnable task = new SchedulingRunnable("ClearWsDataTask", "clear", "0 0 */1 * * ?");
        //每天执行一次任务
        cronTaskRegister.addCronTask("clear_wsData_task", task, "0 0 0 * * ?");
    }

    @Override
    public String getName() {
        return "清理websocket消息任务";
    }
}
