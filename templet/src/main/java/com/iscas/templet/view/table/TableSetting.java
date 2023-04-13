package com.iscas.templet.view.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhuquanwen
 * @date 2017/12/25 17:10
 **/
@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class TableSetting implements Serializable {

    /**
     * 否显示复选框
     */
    protected Boolean checkbox = false;

    /**
     * 表前说明
     * 用不到，会在未来版本删除
     */
    @Deprecated
    protected String frontInfo;

    /**
     * 表后说明
     * 用不到，不i在未来本本删除
     */
    @Deprecated
    protected String backInfo;

    /**
     * 表的标题
     */
    protected String title;

    /**
     * 表的显示形式
     */
    protected TableViewType viewType = TableViewType.multi;

    /**
     * 单元格内可不可编辑
     */
    protected boolean cellEditable = false;

    /**
     * 数据列自生成列说明
     */
    protected List<ButtonSetting> buttonSetting;
}
