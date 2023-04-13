package com.iscas.base.biz.service.common;

import com.iscas.base.biz.util.SpringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 *
 * 此类将弃用，请使用{@link com.iscas.base.biz.util.SpringUtils}
 * spirng 操作
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/26 16:47
 * @since jdk1.8
 */
@Component
@Lazy(value = false)
@Deprecated
public class SpringService implements ApplicationContextAware {
    private static ApplicationContext applicationContext;


    @Deprecated
    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        SpringService.applicationContext = applicationContext;
    }

    /**
     * 弃用，请使用{@link SpringUtils#getApplicationContext()}
     * */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 弃用，请使用{@link SpringUtils#getBean(String)}
     * */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) throws BeansException {
        return (T) applicationContext.getBean(name);
    }

    /**
     * 弃用，请使用{@link SpringUtils#getBean(Class)}}
     * */
    public static <T> T getBean(Class<T> tClass) throws BeansException {
        return applicationContext.getBean(tClass);
    }
}
