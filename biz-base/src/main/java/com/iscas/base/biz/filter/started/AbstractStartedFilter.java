package com.iscas.base.biz.filter.started;

import com.iscas.base.biz.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;

/**
 * 服务启动后过滤器，
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/4/20 9:45
 * @since jdk1.8
 */
@Slf4j
public abstract class AbstractStartedFilter {
    protected AbstractStartedFilter nextFilter;

    /**
     * 如果在初始化Filter方法中，使用了AOP切换数据源，会出现切不回去的问题。
     * 因为多个filter中的函数是一次调用，所有初始化方法调用完全后才会进入biz-mp模块下的MultiDatasourceAspectJExpressionPointcutAdvisor#getAspectJExpressionPointcutAdvisor
     * 中的finally中清除threadlocal。这里执行前强制执行一次，避免在初始化过滤器中切面错乱
     * */
    private void clearDbContextHolder() {
        try {
            Class<?> dbContextHolderClass = Class.forName("com.iscas.biz.mp.config.db.DbContextHolder");
            Method clearDbTypeMethod = dbContextHolderClass.getMethod("clearDbType");
            clearDbTypeMethod.invoke(null);
        } catch (Exception e) {
            log.debug("不存在DbContextHolder类或clearDbType方法，不用清除threadlocal");
        }
    }

    /**
     * 调用下一个过滤器
     * */
    public void doFilter(ApplicationContext applicationContext) {
        //add by zqw 20210531,如果存在DbContextHolder，添加清除处理
        clearDbContextHolder();

        if (nextFilter != null) {
            log.info("========服务启动后过滤器:{}调用开始==============", StringUtils.isEmpty(getName()) ?
                    this.getClass().getName() : getName());
            nextFilter.doFilterInternal(applicationContext);
        } else {
            log.info("========服务启动后过滤器栈调用结束==============");
        }
    }
    /**
     * 处理业务
     * */
    public void doFilterInternal(ApplicationContext applicationContext) {
        doFilter(applicationContext);
    }

    /**
     * 获取过滤器名字
     * */
    @SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
    public abstract String getName();

    public void setNextFilter(AbstractStartedFilter nextFilter) {
        this.nextFilter = nextFilter;
    }
}

