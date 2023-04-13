package com.iscas.biz.calculation.service;

import com.iscas.templet.view.table.ComboboxData;

import java.util.List;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/11 16:18
 */

public interface ProjectService {
    List<ComboboxData> listCalculationSpecificationCombobox();

    boolean deleteByIds(List<Integer> ids);
}
