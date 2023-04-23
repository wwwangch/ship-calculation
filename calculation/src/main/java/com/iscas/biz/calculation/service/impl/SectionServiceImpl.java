package com.iscas.biz.calculation.service.impl;

import com.iscas.base.biz.service.fileserver.FileServerService;
import com.iscas.biz.calculation.entity.db.Section;
import com.iscas.biz.calculation.mapper.ProjectMapper;
import com.iscas.biz.calculation.mapper.SectionMapper;
import com.iscas.biz.calculation.service.SectionService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        MultipartFile sectionFile = section.getSectionFile();
        if (null == sectionFile) {
            throw new RuntimeException("剖面文件不可为空");
        }
        Integer projectId = section.getProjectId();
        if (null == projectMapper.selectById(projectId)) {
            throw new RuntimeException(String.format("项目:[%s]不存在", String.valueOf(projectId)));
        }
        Map<String, String> upload = fileServerService.upload(new MultipartFile[]{sectionFile});
        String originalFilename = sectionFile.getOriginalFilename();
        String path = upload.get(originalFilename);
        section.setSectionFileName(originalFilename);
        section.setSectionFilePath(path);
        section.setCreateTime(new Date());
        return sectionMapper.insert(section);
    }

    @Override
    public Integer update(Section section) throws IOException {
        Integer projectId = section.getProjectId();
        if (null == projectMapper.selectById(projectId)) {
            throw new RuntimeException(String.format("项目:[%s]不存在", String.valueOf(projectId)));
        }

        MultipartFile sectionFile = section.getSectionFile();
        if (null != sectionFile) {
            Map<String, String> upload = fileServerService.upload(new MultipartFile[]{sectionFile});
            String originalFilename = sectionFile.getOriginalFilename();
            String path = upload.get(originalFilename);
            section.setSectionFileName(originalFilename);
            section.setSectionFilePath(path);
        }
        section.setUpdateTime(new Date());
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
