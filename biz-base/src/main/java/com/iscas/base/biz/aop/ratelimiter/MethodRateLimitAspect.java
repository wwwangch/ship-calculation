package com.iscas.base.biz.aop.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;
import com.iscas.base.biz.config.StaticInfo;
import com.iscas.base.biz.util.SpringUtils;
import com.iscas.common.tools.constant.CommonConstant;
import com.iscas.templet.exception.Exceptions;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 方法级别限流
 *
 * @author zhuquanwen
 */
@SuppressWarnings({"unused", "UnstableApiUsage"})
@Aspect
@Component
public class MethodRateLimitAspect {
    private final Logger logger = LoggerFactory.getLogger(MethodRateLimitAspect.class);
    private final Map<String, RateLimiter> rateLimiterMap = new ConcurrentHashMap<>();

    @Around("@annotation(methodRateLimit)")
    public Object before(final ProceedingJoinPoint joinPoint, MethodRateLimit methodRateLimit) throws Throwable {
        //如果未开启限流注解，不进行方法限流逻辑判断
        if (!StaticInfo.ENABLE_RATELIMITER) {
            return joinPoint.proceed();
        }
        //获取request对象
        String remoteAddr = SpringUtils.getRemoteAddr();

        //本地访问不做限制
        if (Objects.equals(CommonConstant.LOCAL_IP, remoteAddr)) {
            return joinPoint.proceed();
        }
        //获取当前的response对象，如果超过超时时间还得不到令牌，返回服务器繁忙的提示
        //生成一个唯一方法名的key,作为Map的键
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        String className = signature.getDeclaringType().getName();
        String key = className + "." + methodName;

        //如果key不存在或更改了permitsPerSencond 创建一个新的令牌桶，否则返回key对应的值
        RateLimiter rateLimiter = rateLimiterMap.compute(key, (k, v) -> v == null || v.getRate() != methodRateLimit.permitsPerSecond() ?
                RateLimiter.create(methodRateLimit.permitsPerSecond()) : v);
        if (!rateLimiter.tryAcquire(methodRateLimit.maxWait(), TimeUnit.MILLISECONDS)) {
            //获取令牌失败，并且超过超时时间
            logger.warn(remoteAddr + "访问方法:" + key + ",短期无法获取令牌");
            throw Exceptions.requestTimeoutRuntimeException("服务器繁忙,请求超时");
        }
        logger.debug(remoteAddr + "访问方法:" + key + ",获取令牌成功");
        return joinPoint.proceed();
    }
}
