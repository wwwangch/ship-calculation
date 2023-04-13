//package com.iscas.base.biz.test.filter;
//
//import com.iscas.base.biz.filter.started.AbstractStartedFilter;
//import com.iscas.base.biz.filter.started.StartedFilterComponent;
//import com.iscas.base.biz.schedule.CronTaskRegister;
//import com.iscas.base.biz.schedule.SchedulingRunnable;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.ApplicationContext;
//
///**
// *
// * @author zhuquanwen
// * @version 1.0
// * @date 2020/4/21 13:50
// * @since jdk1.8
// */
//@StartedFilterComponent(order = 2)
//@Slf4j
//public class TestStartedFilter2 extends AbstractStartedFilter {
//
//    @Override
//    public void doFilterInternal(ApplicationContext applicationContext) {
//        log.info("=============启动后测试过滤器2=================");
//        testTask(applicationContext);
//        super.doFilterInternal(applicationContext);
//    }
//
//    private void testTask(ApplicationContext applicationContext) {
//        CronTaskRegister cronTaskRegister = applicationContext.getBean(CronTaskRegister.class);
//        SchedulingRunnable task = new SchedulingRunnable("TestTask", "test", null);
//        //每30S执行一次任务
//        cronTaskRegister.addCronTask(task, "0/10 * * * * ?");
//    }
//
//    @Override
//    public String getName() {
//        return "测试过滤器2";
//    }
//}
