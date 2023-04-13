package com.iscas.biz.mp.test.service.impl;

import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.mp.test.mapper.MapResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author zhuquanwen
 * @date 2018/7/16 14:45
 **/
@SuppressWarnings({"unused", "rawtypes"})
@Service
@ConditionalOnMybatis
public class MapResultService {
    @Autowired
    private MapResultMapper mapResultMapper;

    public List<Map> selectAll() {
        return mapResultMapper.select();
    }

    public Map selectById(Integer id) {
        return mapResultMapper.selectById(id);
    }
}
