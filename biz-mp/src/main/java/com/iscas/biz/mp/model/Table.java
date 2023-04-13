package com.iscas.biz.mp.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/5/30 9:51
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class Table {
    protected String name;
    protected String comment;

}
