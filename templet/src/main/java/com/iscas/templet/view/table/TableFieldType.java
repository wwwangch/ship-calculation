package com.iscas.templet.view.table;

/**
 * @author zhuquanwen
 * @date 2017/12/25 17:08
 **/
public enum  TableFieldType {
    /**文本*/
    text,
    /**日期*/
    date,
    /**日期时间*/
    datetime,
    /**时间 HH:mm:ss*/
    time,
    /**没有秒的时间*/
    time_no_s,
    /**颜色*/
    color,
    /**普通下拉列表*/
    select,
    /**图片*/
    image,
    /**数值*/
    number,
    /**链接*/
    link,
    /**嵌套一个数组*/
    array,
    /**嵌套一个对象*/
    object,
    /**嵌套一个tab*/
    tab,
    /**textarea*/
    textarea,
    /**嵌套一颗树*/
    tree,
    /**嵌套一个表格*/
    table,
    /**多选下拉列表*/
    multiSelect,
    /**集合但是以逗号分隔显示*/
    split,
    /**文件选择*/
    file,
    /**按钮 */
    button,
    /**表头下拉*/
    headerSelect,
    /**可编辑的下拉菜单*/
    selectCanedit,
    /**级联选择下拉菜单*/
    selectCascader,
    /**年*/
    year,
    /**月*/
    month,
    /**年月日范围*/
    daterange,
    /**年月日时间范围*/
    datetimerange,
    /**（特殊-label在上，input框在下）*/
    verticaltext,
    /**（特殊-宽度100%的文本输入框）*/
    fulltext,
    /**（特殊-宽度100%的多行文本输入框）*/
    bigtextarea,

    /**密码输入框*/
    password
    ;



    //table-view中内置的搜索或使用InputListView表单控件的type类型有：text（输入框）textarea（文本框）email（email）
    //number（数字）select（下拉框）
    //multiSelect（多选下拉框）select-canedit（可编辑的下拉菜单）
    //select-cascader（级联选择下拉菜单）
    //Checkbox/year（年）month（月）date（日期）
    //datetime（年月日时间）daterange（年月日范围）
    //datetimerange（年月日时间范围）verticaltext（特殊-label在上，input框在下）fulltext（特殊-宽度100%的文本输入框）bigtextarea（特殊-宽度100%的多行文本输入框）


	public static TableFieldType analyzeFieldType(String type){
		for(TableFieldType fieldType : TableFieldType.values()){
			if(fieldType.name().equalsIgnoreCase(type)){
				return fieldType;
			}
		}
		return null;
	}
}
