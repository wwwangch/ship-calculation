package com.iscas.templet.view.table;

import com.iscas.templet.common.ResponseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author zhuquanwen
 * @date 2017/12/25 17:03
 **/


@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TableHeaderResponse extends ResponseEntity<TableHeaderResponseData> implements Serializable {

}
