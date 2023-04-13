//package com.iscas.base.biz.test.filter;
//
//import com.iscas.base.biz.filter.started.AbstractStartedFilter;
//import com.iscas.base.biz.filter.started.StartedFilterComponent;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.ApplicationContext;
//
///**
// *
// *
// * @author zhuquanwen
// * @version 1.0
// * @date 2020/4/21 13:50
// * @since jdk1.8
// */
//@StartedFilterComponent(order = 1)
//@Slf4j
//public class TestStartedFilter1 extends AbstractStartedFilter {
//
//    @Override
//    public void doFilterInternal(ApplicationContext applicationContext) {
//        //todo 写业务逻辑
//        System.out.println("这是业务逻辑啦啦啦啦啦啦");
//        super.doFilterInternal(applicationContext);
//    }
//
//    @Override
//    public String getName() {
//        return "测试过滤器1";
//    }
//}
