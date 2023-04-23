package com.iscas.biz.calculation.service;

import com.iscas.biz.calculation.entity.db.Section;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/20 14:09
 */
public interface SectionService {
    int save(Section section) throws IOException;

    Boolean deleteByIds(List<Integer> ids);

    Integer update(Section section) throws IOException;
}
