package com.iscas.base.biz.config;

import org.slf4j.TtlMDCAdapter;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 参考：https://gitee.com/zlt2000/microservices-platform/blob/master/zlt-commons/zlt-log-spring-boot-starter/src/main/java/org/slf4j/TtlMDCAdapter.java
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/2/12 12:07
 */
public class TtlMDCAdapterInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        //加载TtlMDCAdapter实例
        TtlMDCAdapter.getInstance();
    }
}
