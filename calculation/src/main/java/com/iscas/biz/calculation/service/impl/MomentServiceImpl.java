package com.iscas.biz.calculation.service.impl;


import com.iscas.biz.calculation.mapper.MomentMapper;
import com.iscas.biz.calculation.mapper.ProjectMapper;
import com.iscas.biz.calculation.service.MomentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author yichuan@iscas.ac.cn
 * @Date 2023/5/31 14:38
 */
@Service
public class MomentServiceImpl implements MomentService {
    private final MomentMapper momentMapper;
    private final ProjectMapper projectMapper;

    public MomentServiceImpl(MomentMapper momentMapper, ProjectMapper projectMapper) {
        this.momentMapper = momentMapper;
        this.projectMapper = projectMapper;
    }
    public Boolean deleteByIds(List<Integer> ids) {
        try {
            momentMapper.deleteBatchIds(ids);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("删除中拱中垂数据时异常", e);
        }
    }
}
