package com.iscas.biz.calculation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iscas.biz.calculation.entity.db.Bulkhead;
import com.iscas.biz.calculation.mapper.BulkheadMapper;
import com.iscas.biz.calculation.mapper.ProjectMapper;
import com.iscas.biz.calculation.service.BulkheadService;
import com.iscas.datasong.connector.util.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/20 14:09
 */
@Service
public class BulkheadServiceImpl extends ServiceImpl<BulkheadMapper, Bulkhead> implements BulkheadService {

    private final ProjectMapper projectMapper;

    public BulkheadServiceImpl(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    @Override
    public Integer update(Bulkhead bulkhead) {
        Integer projectId = bulkhead.getProjectId();
        if (null == projectMapper.selectById(projectId)) {
            throw new RuntimeException(String.format("项目:[%s]不存在", projectId));
        }
        return this.updateById(bulkhead) ? 1 : 0;
    }

    @Override
    public Boolean deleteByIds(List<Integer> ids) {
        try {
            if (CollectionUtils.isNotEmpty(ids)) {
                return this.removeByIds(ids);
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException("删除剖面数据时异常", e);
        }
    }
}
