package com.iscas.biz.config.log;

import com.iscas.biz.domain.common.LogInfo;

/**
 *
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/2/21 18:09
 * @since jdk1.8
 */
public interface IStoreLogService {
    @SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
    void store(LogInfo logInfo);
}
