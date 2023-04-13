package com.iscas.templet.view.table;



import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuquanwen
 * @date 2017/12/28 10:19
 **/
@SuppressWarnings({"rawtypes", "unused"})
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ComboboxData<T> implements Serializable {
    /**label*/
    protected String label;
    /**value*/
    protected Object value;

    /** data*/
    protected T data;

    protected List<ComboboxData<T>> children;

    /**
     * 通过字符串数组生成通用得下拉列表
     * */
    public static List<ComboboxData> commonCombobox(String[] args) {
        List<ComboboxData> comboboxDatas = new ArrayList<>();
        for (String protocol : args) {
            ComboboxData comboboxData = new ComboboxData();
            comboboxData.setLabel(protocol);
            comboboxData.setValue(protocol);
            comboboxDatas.add(comboboxData);
        }
        return comboboxDatas;
    }

}
