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
 * @date 2017/12/25 16:54
 **/
@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class TableResponseData<T> implements Serializable {
    /**
     * 返回总条目
     */
    protected Long rows = 0L;
    /**
     * 返回的具体数据，是个集合
     */
    private List<T> data;

}
