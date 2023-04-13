package com.iscas.templet.view.chart.stackbar;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/9/10 13:34
 * @since jdk1.8
 */
@Getter
@Setter
@Deprecated
public class StackBarAngleAxis implements Serializable {
    /**暂时固定为string使用默认值*/
    protected String type = "category";

    /**x轴数据，一维数组例如： ['周一', '周二', '周三', '周四', '周五', '周六', '周日']*/
    protected List<String> data;

}
