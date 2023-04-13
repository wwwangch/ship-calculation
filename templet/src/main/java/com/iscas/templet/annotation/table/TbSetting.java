package com.iscas.templet.annotation.table;

import com.iscas.templet.view.table.TableViewType;

import java.lang.annotation.*;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/8/20 21:26
 * @since jdk1.8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface TbSetting {

    /**表的标题*/
    String title() default "";

    /**
     * 是否显示复选框
     * */
    boolean checkbox() default false;

    /**表的显示形式*/
    TableViewType viewType() default TableViewType.multi;

    /**单元格内可不可编辑*/
    boolean cellEditable() default false;

}
