package com.iscas.templet.view.chart.base;

import com.iscas.templet.common.ResponseEntity;
import com.iscas.templet.view.v2.ChartResponseData;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author zhuquanwen
 * @date 2018/4/11 11:31
 **/
@Getter
@Setter
@Deprecated
public class ChartResponse extends ResponseEntity<ChartResponseData> implements Serializable {
    protected Object other;

}
