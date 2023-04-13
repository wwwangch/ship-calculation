package com.iscas.templet.view.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author zhuquanwen
 * @date 2017/12/25 16:40
 **/
@SuppressWarnings({"rawtypes", "unused", "unchecked"})
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class TableSearchRequest<T> implements Serializable {
    /**
     * 当前页码，默认为1
     */
    protected Integer pageNumber = 1;
    /**
     * 每页显示条目，默认为10
     */
    protected Integer pageSize = Integer.MAX_VALUE;
    /**
     * 排序的列
     */
    protected String sortField;
    /**
     * 升序或者降序
     */
    protected TableSortType sortOrder = TableSortType.asc;
    /**
     * 查询条件(扩展用)
     */
    protected T filter;
    /**
     * 查询方式，为了与原有方式兼容，扩展一个查询方式
     */
    protected Map<String, TableSearchType> searchType;

    /**
     * 下拉列表查询条件
     */
    protected Map<String, List> optionsFilter;


    /**
     * 通过查询的key获取查询的值
     */
    public Object[] getSearchValByKey(String key) {
        if (filter != null) {
            Map filterMap = (Map) filter;
            List<Object> searchVals = (List<Object>) filterMap.get(key);
            if (searchVals != null && searchVals.size() >= 1) {
                Object[] result;
                if (searchVals.size() == 1) {
                    result = new Object[1];
                    result[0] = searchVals.get(0);
                } else {
                    result = new Object[2];
                    result[0] = searchVals.get(0);
                    result[1] = searchVals.get(1);
                }
                return result;
            }
        }
        return null;
    }


}
