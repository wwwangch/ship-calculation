package com.iscas.biz.service.common;

import com.baomidou.mybatisplus.extension.service.IService;
import com.iscas.biz.domain.common.LogInfo;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/2/20 18:26
 * @since jdk1.8
 */
@SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
public interface LogInfoService extends IService<LogInfo> {

    void deleteDataByTime(String holdPeriod);
}
