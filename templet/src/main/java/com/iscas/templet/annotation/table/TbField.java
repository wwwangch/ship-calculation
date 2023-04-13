package com.iscas.templet.annotation.table;

import com.iscas.templet.view.table.TableFieldType;
import com.iscas.templet.view.table.TableSearchType;

import java.lang.annotation.*;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/8/20 21:26
 * @since jdk1.8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface TbField {

    /**表字段名称,如果不指定使用当前的字段名称*/
    String field();
    /**表字段显示名称,如果不指定使用field的*/
    String header() ;
    /**是否可编辑，默认不可编辑*/
    boolean editable() default true;
    /**此列是否支持排序，默认不支持*/
    boolean sortable() default false;
    /**是不是可以新增*/
    boolean addable() default true;
    /**此列的类型*/
    TableFieldType type() default TableFieldType.text;
    /**是否查询*/
     boolean search() default false;
    /**查询方式*/
    TableSearchType searchType() default TableSearchType.exact;
    /**是不是隐藏*/
    boolean hidden() default false;
    /**搜索方式*/
    String searchWay() default "";
    /**如果是下拉列表,返回的下拉列表信息,当前类的静态属性的名称*/
    String option() default "";
    /**如果是下拉列表,options为null,可以指定一个URL获取*/
    String selectUrl() default "";

    /**
     * 校验规则
     * */
    TbFieldRule rule() default @TbFieldRule(desc = "");

}
