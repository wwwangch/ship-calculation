package com.iscas.biz.service.common;

import com.baomidou.mybatisplus.extension.service.IService;
import com.iscas.biz.domain.common.DictData;
import com.iscas.templet.exception.BaseException;

import java.util.List;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/2/25 16:12
 * @since jdk1.8
 */
@SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
public interface DictDataService extends IService<DictData> {

    boolean deleteByIds(List<Object> ids) throws BaseException;

}
