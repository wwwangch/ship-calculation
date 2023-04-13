package com.iscas.biz.calculation.service.impl;

import com.iscas.biz.calculation.mapper.BulbFlatMapper;
import com.iscas.biz.calculation.service.BulbFlatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/13 21:14
 */
@Service
@Slf4j
public class BulbFlatServiceImpl implements BulbFlatService {
    private final BulbFlatMapper bulbFlatMapper;

    public BulbFlatServiceImpl(BulbFlatMapper bulbFlatMapper) {
        this.bulbFlatMapper = bulbFlatMapper;
    }

    @Override
    public boolean deleteByIds(List<Integer> ids) {
        bulbFlatMapper.deleteBatchIds(ids);
        return true;
    }
}
