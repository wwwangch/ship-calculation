package com.iscas.biz.calculation.service.impl;

import com.iscas.base.biz.service.fileserver.FileServerService;
import com.iscas.biz.calculation.entity.db.Section;
import com.iscas.biz.calculation.mapper.ProjectMapper;
import com.iscas.biz.calculation.mapper.SectionMapper;
import com.iscas.biz.calculation.service.SectionService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/20 14:09
 */
@Service
public class SectionServiceImpl implements SectionService {
    private final FileServerService fileServerService;

    private final SectionMapper sectionMapper;

    private final ProjectMapper projectMapper;

    public SectionServiceImpl(FileServerService fileServerService, SectionMapper sectionMapper, ProjectMapper projectMapper) {
        this.fileServerService = fileServerService;
        this.sectionMapper = sectionMapper;
        this.projectMapper = projectMapper;
    }

    @Override
    public int save(Section section) throws IOException {
        return sectionMapper.insert(section);
    }

    @Override
    public Integer update(Section section) throws IOException {
        Integer projectId = section.getProjectId();
        if (null == projectMapper.selectById(projectId)) {
            throw new RuntimeException(String.format("项目:[%s]不存在", String.valueOf(projectId)));
        }
        return sectionMapper.updateById(section);
    }

    @Override
    public Boolean deleteByIds(List<Integer> ids) {
        try {
            sectionMapper.deleteBatchIds(ids);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("删除剖面数据时异常", e);
        }
    }
}
