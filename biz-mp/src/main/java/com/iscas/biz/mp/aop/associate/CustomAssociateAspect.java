package com.iscas.biz.mp.aop.associate;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/9/4 11:25
 * @since jdk1.8
 */
@Aspect
@Component
@Slf4j
@Deprecated
public class CustomAssociateAspect {

    @Before("@annotation(associates)")
    public void before( CustomAssociates associates) throws Throwable {
        AssoThreadLocal.ASSOCIATES_THREAD_LOCAL.set(associates);
    }
}
