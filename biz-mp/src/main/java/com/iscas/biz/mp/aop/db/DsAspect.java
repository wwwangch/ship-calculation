package com.iscas.biz.mp.aop.db;

import com.iscas.biz.mp.config.db.DbContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/8/31 13:23
 * @since jdk1.8
 */
@SuppressWarnings("unused")
@Aspect
@Component
@Slf4j
public class DsAspect {

    @Pointcut("@within(com.iscas.biz.mp.aop.db.DS) || @annotation(com.iscas.biz.mp.aop.db.DS)")
    public void aopPointCut() {

    }

    @Around(value = "com.iscas.biz.mp.aop.db.DsAspect.aopPointCut()")
    public Object around(final ProceedingJoinPoint joinPoint) throws Throwable {
        DS ds = getAnnotation(joinPoint, DS.class);
        if (ds == null) {
            return joinPoint.proceed();
        }
        try {
            DbContextHolder.setDbType(ds.value());
            log.debug("正在访问{}数据源...", ds.value());
            return joinPoint.proceed();
        } finally {
            DbContextHolder.clearDbType();
        }
    }

    /**
     * 通过连接点对象获取其方法或类上的注解
     *
     * @param point  连接点
     * @param tClass 注解类型
     * @return T 注解
     * @date 2023/2/21
     */
    public <T extends Annotation> T getAnnotation(ProceedingJoinPoint point, Class<T> tClass) {
        T t = null;
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null && method.isAnnotationPresent(tClass)) {
            t = method.getAnnotation(tClass);
        }
        if (t == null && method.getDeclaringClass().isAnnotationPresent(tClass)) {
            t = method.getDeclaringClass().getAnnotation(tClass);
        }
        return t;
    }
}
