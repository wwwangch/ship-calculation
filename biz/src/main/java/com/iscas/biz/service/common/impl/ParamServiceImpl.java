package com.iscas.biz.service.common.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iscas.base.biz.config.Constants;
import com.iscas.base.biz.util.CacheUtils;
import com.iscas.biz.domain.common.Param;
import com.iscas.biz.mapper.common.ParamMapper;
import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.service.common.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/2/26 9:52
 * @since jdk1.8
 */
@Service
@ConditionalOnMybatis
public class ParamServiceImpl extends ServiceImpl<ParamMapper, Param> implements ParamService {

    @Autowired
    private ParamMapper paramMapper;

    /**
     * 根据ids删除对应的参数，系统类参数不允许删除
     */
    @Override
    public boolean deleteByIds(List<Object> ids) {
        //系统类的参数不允许删除
        UpdateWrapper<Param> wrapper = new UpdateWrapper();
        wrapper.in("id", ids);
        wrapper.ne("param_type", "系统类");
        List<Param> paramList = paramMapper.selectList(wrapper);
        List<String> keys = Optional.ofNullable(paramList)
                .map(params -> params.stream().map(Param::getParamKey).toList())
                .orElse(new ArrayList<>());
        CacheUtils.evictCache(Constants.CACHE_PARAM_NAME, keys);
        return this.remove(wrapper);
    }

    /**
     * 根据 参数的键 获取 参数的值
     */
    @Override
    @Cacheable(value = Constants.CACHE_PARAM_NAME, key = "#paramKey", condition = "#result!=\"\"")
    public String getParamValue(String paramKey) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("param_key", paramKey);
        Param param = this.getOne(wrapper);
        return Optional.ofNullable(param).map(Param::getParamValue).orElse("");
    }

}
