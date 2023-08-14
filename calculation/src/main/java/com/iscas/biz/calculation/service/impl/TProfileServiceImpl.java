package com.iscas.biz.calculation.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.iscas.base.biz.util.SpringUtils;
import com.iscas.biz.calculation.entity.db.TProfile;
import com.iscas.biz.calculation.grpc.service.AlgorithmGrpc;
import com.iscas.biz.calculation.mapper.TProfileMapper;
import com.iscas.biz.calculation.service.TProfileService;
import com.iscas.common.web.tools.file.FileDownloadUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    private final AlgorithmGrpc algorithmGrpc;

    public TProfileServiceImpl(TProfileMapper tProfileMapper, AlgorithmGrpc algorithmGrpc) {
        this.tProfileMapper = tProfileMapper;
        this.algorithmGrpc = algorithmGrpc;
    }

    @Override
    public boolean deleteByIds(List<Integer> ids) {
        tProfileMapper.deleteBatchIds(ids);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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
        if (CollectionUtils.isEmpty(tProfiles)) {
            return 0;
        }
        //过滤出需要计算的
        List<TProfile> needCal = tProfiles.stream().filter(tProfile -> StringUtils.isNotBlank(tProfile.getModel())).toList();
        if (CollectionUtils.isEmpty(needCal)) {
            return 0;
        }
        List<TProfile> calTProfileProperty = algorithmGrpc.calTProfileProperty(needCal);

        if (CollectionUtils.isNotEmpty(calTProfileProperty)) {
            this.saveBatch(calTProfileProperty);
        }
        return calTProfileProperty.size();
    }

    /**
     * 下载模板
     */
    @Override
    public void downloadTemplate() throws IOException {
        FileDownloadUtils.downFile(SpringUtils.getRequest(), SpringUtils.getResponse(), ResourceUtils.getFile("classpath:profile/templates/TProfile.xlsx"), "T型材模板.xlsx");
    }
}
