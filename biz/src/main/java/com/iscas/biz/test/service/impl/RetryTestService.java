package com.iscas.biz.test.service.impl;

import com.iscas.common.tools.core.random.RandomStringUtils;
import com.iscas.templet.exception.BaseException;
import com.iscas.templet.exception.Exceptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Random;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/5/7 17:57
 * @since jdk1.8
 */
@Slf4j
@Service
public class RetryTestService {

    /**
     * 使用方式1，如果出现异常调用recover补偿
     *
     * Retryable参数说明如下：
     * interceptor：可以通过该参数，指定方法拦截器的bean名称
     * value：抛出指定异常才会重试
     * include：和value一样，默认为空，当exclude也为空时，默认所以异常
     * exclude：指定不处理的异常
     * maxAttempts：最大重试次数，默认3次
     * backoff：重试等待策略，默认使用@Backoff，@Backoff的value默认为1000L，我们设置为2000L；multiplier（指定延迟倍数）默认为0，表示固定暂停1秒后进行重试，如果把multiplier设置为1.5，则第一次重试为2秒，第二次为3秒，第三次为4.5秒。
     * */
    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 2000L, multiplier = 1.5))
    public String server() throws BaseException {
        log.debug("===开始测试Retry===");
        if (new Random().nextBoolean()) {
            throw Exceptions.baseException("出错啦");
        }
        log.debug("===测试Retry结束===");
        return RandomStringUtils.randomStr(5);
    }

    /**
     * 如果使用注解的话,这个recover貌似只能写在本类中
     * */
    @Recover
    public String recover(BaseException e) {
        log.warn("出错啦，补偿操作", e);
        return e.getMessage();
    }

    /**
     * 测试不用注解的方式
     * */
    public String server2(){
        final RetryTemplate retryTemplate = new RetryTemplate();
        final SimpleRetryPolicy policy = new SimpleRetryPolicy(3, Collections.<Class<? extends Throwable>, Boolean>
                singletonMap(Exception.class, true));
        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(100);
        retryTemplate.setRetryPolicy(policy);
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
        final RetryCallback<String, Exception> retryCallback = context -> {
            System.out.println("do some thing");
            //设置context一些属性,给RecoveryCallback传递一些属性
            context.setAttribute("key1", "value1");
            System.out.println(context.getRetryCount());
            throw Exceptions.exception("exception");
            //                return null;
        };

        // 如果RetryCallback执行出现指定异常, 并且超过最大重试次数依旧出现指定异常的话,就执行RecoveryCallback动作
        final RecoveryCallback<String> recoveryCallback = context -> {
            System.out.println("do recory operation");
            System.out.println(context.getAttribute("key1"));
            return "出错啦拉拉";
        };

        try {
            final String execute = retryTemplate.execute(retryCallback, recoveryCallback);
            return execute;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
