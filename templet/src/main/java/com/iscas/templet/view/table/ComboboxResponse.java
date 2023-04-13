package com.iscas.templet.view.table;

import com.iscas.templet.common.ResponseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/2/20 15:36
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ComboboxResponse<T> extends ResponseEntity<List<ComboboxData<T>>> {
}
