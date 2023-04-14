package com.iscas.biz.calculation.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.iscas.biz.calculation.entity.db.BulbFlat;
import com.iscas.biz.calculation.mapper.BulbFlatMapper;
import com.iscas.biz.calculation.service.BulbFlatService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/13 21:14
 */
@Service
@Slf4j
public class BulbFlatServiceImpl extends ServiceImpl<BulbFlatMapper, BulbFlat> implements BulbFlatService {
    private final BulbFlatMapper bulbFlatMapper;

    public BulbFlatServiceImpl(BulbFlatMapper bulbFlatMapper) {
        this.bulbFlatMapper = bulbFlatMapper;
    }

    @Override
    public boolean deleteByIds(List<Integer> ids) {
        bulbFlatMapper.deleteBatchIds(ids);
        return true;
    }

    @Override
    public int uploadFile(MultipartFile file) {
        List<BulbFlat> bulbFlats = Lists.newArrayList();
        try {
            bulbFlats.addAll(EasyExcel.read(file.getInputStream())
                    .autoCloseStream(true)
                    .sheet(0)
                    .autoTrim(true)
                    .head(BulbFlat.class)
                    .headRowNumber(2)
                    .doReadSync());
        } catch (Exception e) {
            throw new RuntimeException("文件解析失败,请检查excel文件内容格式");
        }
        if (CollectionUtils.isNotEmpty(bulbFlats)) {
            this.saveBatch(bulbFlats);
        }
        return bulbFlats.size();
    }
}
