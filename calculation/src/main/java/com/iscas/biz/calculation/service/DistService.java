package com.iscas.biz.calculation.service;

import com.iscas.biz.calculation.entity.db.Dist;

import java.io.IOException;
import java.util.Map;

/**
 * @author zhaotianci@iscas.ac.cn
 * @date 2023/5/28 0:31
 */
public interface DistService {

    int remove(Integer projectId);

    Dist calculateAndSave(Integer projectId);

    Boolean reset(Integer projectId);

    void export(Integer projectId) throws IOException;

    Dist getData(Integer projectId);
}
