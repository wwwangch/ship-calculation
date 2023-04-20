package com.iscas.biz.calculation.service;

import com.iscas.biz.calculation.entity.db.ShipParam;
import com.iscas.templet.exception.ValidDataException;
import com.iscas.templet.view.table.ComboboxData;

import java.util.List;
import java.util.Map;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/11 15:46
 */
public interface ShipParamService {
    int saveWithFile(ShipParam shipParam);

    int updateById(Map<String, Object> data) throws ValidDataException;

    boolean deleteByIds(List<Integer> ids);

    List<ComboboxData> listShipTypeCombobox();

    int save(Map<String, Object> shipParam) throws ValidDataException;

    List<ComboboxData> listNavigationAreaCombobox();
}
