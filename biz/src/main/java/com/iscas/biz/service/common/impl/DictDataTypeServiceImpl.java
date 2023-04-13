package com.iscas.biz.service.common.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iscas.base.biz.config.Constants;
import com.iscas.base.biz.util.CacheUtils;
import com.iscas.biz.domain.common.DictData;
import com.iscas.biz.domain.common.DictDataType;
import com.iscas.biz.mapper.common.DictDataMapper;
import com.iscas.biz.mapper.common.DictDataTypeMapper;
import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.service.common.DictDataTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/3/1 15:38
 * @since jdk1.8
 */
@Service
@ConditionalOnMybatis
public class DictDataTypeServiceImpl extends ServiceImpl<DictDataTypeMapper, DictDataType> implements DictDataTypeService {

    @Autowired
    private DictDataMapper dictDataMapper;
    @Autowired
    private DictDataTypeMapper dictDataTypeMapper;

    /**
     * 通过ids删除枚举数据，ids属于同一个枚举项，系统类枚举不允许删除
     */
    @Override
    public boolean deleteByIds(List<Object> ids) {

        QueryWrapper<DictDataType> wrapper = new QueryWrapper<>();
        wrapper.in("id", ids);
        List<DictDataType> dictList = dictDataTypeMapper.selectList(wrapper);

        String dictDataType = Optional.ofNullable(dictList)
                .filter(dicts -> dicts.size() > 0)
                .map(dicts -> dicts.stream().findAny().get().getDictDataType())
                .orElse("");
        if (StringUtils.isNotBlank(dictDataType)) {
            //检查要删除的字典是否是系统类
            QueryWrapper dictDataWrapper = new QueryWrapper();
            dictDataWrapper.eq("dict_data_type", dictDataType);
            DictData dictData = dictDataMapper.selectOne(dictDataWrapper);
            Optional.ofNullable(dictData)
                    .filter(dict -> !dict.getDictType().equals("系统类"))
                    .ifPresent(dict -> {
                        //清理缓存
                        CacheUtils.evictCache(Constants.CACHE_DICT_NAME, Arrays.asList(dictDataType));
                        //删除字典数据类型表
                        UpdateWrapper<DictDataType> deleteWrapper = new UpdateWrapper();
                        deleteWrapper.in("id", ids);
                        dictDataTypeMapper.delete(deleteWrapper);
                    });
        }
        return true;
    }

    /**
     * 获取enum的 key，value组成的map
     */
    @Override
    @Cacheable(value = Constants.CACHE_DICT_NAME, key = "#dictKey")
    public Map getDictValue(String dictKey) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("dict_data_type", dictKey);
        List<DictDataType> dictDataTypeList = dictDataTypeMapper.selectList(wrapper);
        Map valueMap = new HashMap<>();
        Optional.ofNullable(dictDataTypeList)
                .ifPresent(dictDataTypes -> dictDataTypes.stream()
                        .forEach(dictDataType -> valueMap.put(dictDataType.getDictDataKey(), dictDataType.getDictDataValue())));
        return valueMap;
    }

}
