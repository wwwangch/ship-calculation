package com.iscas.biz.config;

import com.iscas.base.biz.config.StaticInfo;
import com.iscas.base.biz.util.AuthContextHolder;
import com.iscas.base.biz.util.SpringUtils;
import com.iscas.templet.common.ResponseEntity;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 这个类没有写在base-biz下的原因是AOP 切入点 对应表达式的包名 没法在父模块定义
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/10/9 13:56
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "rawtypes"})
@Aspect
@Component
@Slf4j
public class ControllerAspect {

    /**
     * 定义拦截规则：拦截com.iscas.biz.controller..*(..))包下面的所有类中
     */
    @Pointcut("execution(* com.iscas.biz.controller..*.*(..)) || execution(* com.iscas.biz.test.controller..*.*(..))")
    public void controllerMethodPointcut() {
    }

    @Around(value = "controllerMethodPointcut()")
    public Object around(final ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        StaticInfo.START_TIME_THREAD_LOCAL.set(start);
        HttpServletRequest request = SpringUtils.getRequest();
        // 记录下请求内容
        if (request != null) {
            log.debug("URL : " + request.getRequestURL().toString());
            log.debug("HTTP_METHOD : " + request.getMethod());
            log.debug("IP : " + request.getRemoteAddr());
            log.debug("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            log.trace("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        }

        try {
            Object result = joinPoint.proceed();
            if (request != null) {
                //如果是ResponseEntity类型那么直接注入值
                if (result instanceof ResponseEntity responseEntity) {
                    responseEntity.setTookInMillis(System.currentTimeMillis() - start);
                    //注入requestURL
                    responseEntity.setRequestURL(request.getRequestURI());
                }
                // 处理完请求，返回内容
                if (result != null) {
                    log.trace("Response:" + result);
                }
            }
            return result;
        } finally {
//            StaticInfo.START_TIME_THREAD_LOCAL.remove();
            AuthContextHolder.removeContext();
        }

    }
}
