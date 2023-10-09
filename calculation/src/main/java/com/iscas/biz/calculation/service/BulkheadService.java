package com.iscas.biz.calculation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.iscas.biz.calculation.entity.db.Bulkhead;
import com.iscas.biz.calculation.entity.db.Section;

import java.io.IOException;
import java.util.List;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/20 14:09
 */
public interface BulkheadService extends IService<Bulkhead> {

    Boolean deleteByIds(List<Integer> ids);

    Integer update(Bulkhead bulkhead) throws IOException;

    void saveCompartment(Object bulkheadFilePath);

    void downloadTemplate() throws IOException;
}
