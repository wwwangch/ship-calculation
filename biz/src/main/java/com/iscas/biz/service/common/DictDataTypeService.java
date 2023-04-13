package com.iscas.biz.service.common;

import com.baomidou.mybatisplus.extension.service.IService;
import com.iscas.biz.domain.common.DictDataType;

import java.util.List;
import java.util.Map;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/3/1 15:37
 * @since jdk1.8
 */
@SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
public interface DictDataTypeService extends IService<DictDataType> {

    boolean deleteByIds(List<Object> ids);

    Map getDictValue(String dictKey);

}
