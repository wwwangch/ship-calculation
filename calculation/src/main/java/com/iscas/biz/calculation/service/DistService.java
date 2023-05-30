package com.iscas.biz.calculation.service;

import com.iscas.biz.calculation.entity.db.Dist;

import java.io.IOException;

/**
 * @author zhaotianci@iscas.ac.cn
 * @date 2023/5/28 0:31
 */
public interface DistService {

    int remove(Integer projectId);

    Dist calculateAndSave(Integer projectId, Integer sectionId);

    Boolean reset(Integer projectId, Integer sectionId);

    void export(Integer projectId, Integer sectionId) throws IOException;

    Dist getData(Integer projectId, Integer sectionId);
}
