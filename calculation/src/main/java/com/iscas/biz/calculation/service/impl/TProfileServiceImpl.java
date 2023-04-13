package com.iscas.biz.calculation.service.impl;

import com.iscas.biz.calculation.mapper.TProfileMapper;
import com.iscas.biz.calculation.service.TProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/13 21:12
 */
@Service
@Slf4j
public class TProfileServiceImpl implements TProfileService {
    private final TProfileMapper tProfileMapper;

    public TProfileServiceImpl(TProfileMapper tProfileMapper) {
        this.tProfileMapper = tProfileMapper;
    }

    @Override
    public boolean deleteByIds(List<Integer> ids) {
        tProfileMapper.deleteBatchIds(ids);
        return true;
    }
}
