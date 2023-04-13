package com.iscas.biz.service.common;

import com.baomidou.mybatisplus.extension.service.IService;
import com.iscas.biz.domain.common.Param;

import java.util.List;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/2/26 9:51
 * @since jdk1.8
 */
public interface ParamService extends IService<Param> {

    boolean deleteByIds(List<Object> ids);

    String getParamValue(String paramKey);

}
