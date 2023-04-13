package com.iscas.templet.view.chart.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 曲线图标题信息
 * @author zhuquanwen
 * @date 2018/5/18 16:29
 **/
@Getter
@Setter
@Deprecated
public class ChartTitle implements Serializable {
    /**标题文本*/
    protected String text = "";
    protected String link = "";
    protected boolean showtitle = true;
    protected String subtext = "";
    protected String sublink = "";


}
