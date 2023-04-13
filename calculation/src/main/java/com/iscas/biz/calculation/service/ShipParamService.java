package com.iscas.biz.calculation.service;

import com.iscas.biz.calculation.entity.db.ShipParam;
import com.iscas.biz.calculation.mapper.ShipParamMapper;
import com.iscas.templet.exception.ValidDataException;
import com.iscas.templet.view.table.ComboboxData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/11 15:46
 */
public interface ShipParamService {
    int saveWithFile(ShipParam shipParam);

    int updateById(ShipParam shipParam) throws ValidDataException;

    boolean deleteByIds(List<Integer> ids);

    List<ComboboxData> listShipTypeCombobox();

    int save(ShipParam shipParam) throws ValidDataException;
}
