package com.iscas.biz.calculation.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.iscas.biz.calculation.entity.db.TProfile;
import com.iscas.biz.calculation.mapper.TProfileMapper;
import com.iscas.biz.calculation.service.TProfileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/13 21:12
 */
@Service
@Slf4j
public class TProfileServiceImpl extends ServiceImpl<TProfileMapper, TProfile> implements TProfileService {
    private final TProfileMapper tProfileMapper;

    public TProfileServiceImpl(TProfileMapper tProfileMapper) {
        this.tProfileMapper = tProfileMapper;
    }

    @Override
    public boolean deleteByIds(List<Integer> ids) {
        tProfileMapper.deleteBatchIds(ids);
        return true;
    }

    @Override
    public int uploadFile(MultipartFile file) {
        List<TProfile> tProfiles = Lists.newArrayList();
        try {
            tProfiles.addAll(EasyExcel.read(file.getInputStream())
                    .autoCloseStream(true)
                    .sheet(0)
                    .autoTrim(true)
                    .head(TProfile.class)
                    .headRowNumber(2)
                    .doReadSync());
        } catch (Exception e) {
            throw new RuntimeException("文件解析失败,请检查excel文件内容格式");
        }
        if (CollectionUtils.isNotEmpty(tProfiles)) {
            this.saveBatch(tProfiles);
        }
        return tProfiles.size();
    }
}
