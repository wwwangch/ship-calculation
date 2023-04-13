package com.iscas.templet.view.table;

import com.iscas.templet.common.ResponseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author zhuquanwen
 * @date 2017/12/25 16:40
 **/
@SuppressWarnings({"unused", "rawtypes"})
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TableResponse<T> extends ResponseEntity<TableResponseData<T>> implements Serializable{
    public TableResponse(){}
    public TableResponse(Integer status, String message) {
        super(status, message);
    }
}
