package com.iscas.biz.config;

import com.iscas.base.biz.schedule.CustomThreadPoolTaskExecutor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/2/26 10:41
 * @since jdk1.8
 */
@SuppressWarnings("unused")
@AutoConfiguration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer, BizConstant {
    private static final int ASYNC_KEEPALIVE_SECONDS = 60;

    private static final int  ASYNC_QUEUE_CAPACITY = 20000;

    @Bean("asyncExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new CustomThreadPoolTaskExecutor();
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
        executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors() * 2);
        executor.setQueueCapacity(20000);
        executor.setKeepAliveSeconds(ASYNC_KEEPALIVE_SECONDS);
        executor.setThreadNamePrefix(ASYNC_EXECUTOR_NAME_PREFIX);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }


}
