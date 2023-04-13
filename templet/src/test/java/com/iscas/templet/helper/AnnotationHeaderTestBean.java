package com.iscas.templet.helper;

import com.iscas.templet.annotation.table.TbField;
import com.iscas.templet.annotation.table.TbFieldRule;
import com.iscas.templet.annotation.table.TbSetting;
import com.iscas.templet.exception.HeaderException;
import com.iscas.templet.view.table.*;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/8/24 21:53
 * @since jdk1.8
 */
@TbSetting(title = "测试表头", checkbox = true, viewType = TableViewType.multi, cellEditable = false)
public class AnnotationHeaderTestBean implements BaseTb {


    @TbField(
            field = "id", header = "id", editable = true, sortable = false, addable = true,
            type = TableFieldType.text, search = false, searchType = TableSearchType.exact, hidden = true,
            option = "", selectUrl = "", rule = @TbFieldRule(desc = "id不能为空",
            required = true, reg = "", maxLength = -1, minLength = -1,
            distinct = false, highValue = "", lowValue = "",
            containsHigh = false, containsLow = false))
    private Integer id;

    @TbField(
            field = "name", header = "名称", editable = true, sortable = false, addable = true,
            option = "", selectUrl = "", rule = @TbFieldRule(desc = "名称啦啦啦啦啦",
            required = false, reg = "[abc]{1,}", maxLength = 20, minLength = 3))
    private String name;

    @Override
    public void headerCallback(TableHeaderResponseData thrd) throws HeaderException {
        thrd.getSetting().setTitle("这是一个测试标题");
    }
}
