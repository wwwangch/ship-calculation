package com.iscas.templet.view.v2;

import com.iscas.templet.common.ResponseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author zhuquanwen
 * @date 2018/4/11 11:31
 **/
@SuppressWarnings("unused")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ChartResponse extends ResponseEntity<ChartResponseData> implements Serializable {
}
