package com.iscas.templet.view.v2;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/3/12 8:53
 * @since jdk1.8
 */
@Data
@ToString(callSuper = true)
public class ChartResponseData implements Serializable {
    protected DataSet dataset;
}
