package com.iscas.biz.calculation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.iscas.biz.calculation.entity.db.BulbFlat;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/13 21:05
 */
public interface BulbFlatService extends IService<BulbFlat> {
    boolean deleteByIds(List<Integer> ids);

    int uploadFile(MultipartFile file);
}
