package com.iscas.templet.helper;

import com.alibaba.fastjson.JSON;
import com.iscas.templet.annotation.table.TbField;
import com.iscas.templet.annotation.table.TbFieldRule;
import com.iscas.templet.annotation.table.TbSetting;
import com.iscas.templet.exception.Exceptions;
import com.iscas.templet.exception.HeaderException;
import com.iscas.templet.view.table.*;
import com.iscas.templet.view.validator.Rule;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/8/24 21:50
 * @since jdk1.8
 */
@SuppressWarnings("rawtypes")
public class HeaderHelper {
    private HeaderHelper() {
    }

    /**
     * 将实体转为表头
     */
    public static TableHeaderResponseData convertToHeader(Class clazz) throws HeaderException {
        TableHeaderResponseData headerData = new TableHeaderResponseData();
        //构建tableSetting
        tableSettingHandle(clazz, headerData);

        //构建TableField
        tableFieldsHandle(clazz, headerData);

        //处理回调，为表头添加个性化处理
        callbackHandle(clazz, headerData);
        return headerData;
    }

    private static void tableFieldsHandle(Class clazz, TableHeaderResponseData headerData) {
        List<TableField> cols = new ArrayList<>();
//        Field[] declaredFields = clazz.getDeclaredFields();
        Field[] declaredFields = ReflectHelper.getFieldsDirectly(clazz);
        for (Field declaredField : declaredFields) {
            TbField tbField = declaredField.getAnnotation(TbField.class);
            if (tbField != null) {
                TableField tableField = new TableField();
                List<ComboboxData> comboboxDatas = null;
                if (tbField.option() != null && !"".equals(tbField.option().strip())) {
                    comboboxDatas = JSON.parseArray(tbField.option(),ComboboxData.class);
                }
                tableField.setField(tbField.field())
                        .setHeader(tbField.header())
                        .setEditable(tbField.editable())
                        .setSortable(tbField.sortable())
                        .setAddable(tbField.addable())
                        .setType(tbField.type())
                        .setSearch(tbField.search())
                        .setSearchType(tbField.searchType())
                        .setHidden(tbField.hidden())
                        .setOption(comboboxDatas)
                        .setSearchWay(Objects.equals("", tbField.searchWay()) ? null : tbField.searchWay())
                        .setSelectUrl(Objects.equals("", tbField.selectUrl()) ? null : tbField.selectUrl());

                //构建校验规则
                TbFieldRule tableFieldRule = tbField.rule();
                if (tableFieldRule != null) {
                    String desc = tableFieldRule.desc();
                    if (!Objects.equals("", desc)) {
                        Rule rule = new Rule();
                        rule.setRequired(tableFieldRule.required())
                                .setDesc(tableFieldRule.desc())
                                .setReg("".equals(tableFieldRule.reg()) ? null : tableFieldRule.reg())
                                .setDistinct(tableFieldRule.distinct())
                                .setContainsHigh(tableFieldRule.containsHigh())
                                .setContainsLow(tableFieldRule.containsLow())
                                .setHighVal("".equals(tableFieldRule.highValue()) ? null : tableFieldRule.highValue())
                                .setLowVal("".equals(tableFieldRule.lowValue()) ? null : tableFieldRule.lowValue())
                                .setMustIn(tableFieldRule.mustIn())
                                .setMustNotIn(tableFieldRule.mustNotIn());
                        Map<String, Integer> length = null;
                        int max = tableFieldRule.maxLength();
                        int min = tableFieldRule.minLength();
                        if (max != -1) {
                            length = new HashMap<>(2);
                            length.put("max", max);
                        }
                        if (min != -1) {
                            if (length == null) {
                                length = new HashMap<>(2);
                            }
                            length.put("min", min);
                        }
                        rule.setLength(length);
                        tableField.setRule(rule);
                    }
                }

                //下拉列表
                //todo 暂不处理
//                    /**如果是下拉列表,返回的下拉列表信息,当前类的静态属性的名称*/
//                    String option();

                cols.add(tableField);
            }
        }

        headerData.setCols(cols);
    }

    @SuppressWarnings("unchecked")
    private static void tableSettingHandle(Class clazz, TableHeaderResponseData headerData) throws HeaderException {
        TbSetting tbs = (TbSetting) clazz.getAnnotation(TbSetting.class);
        if (tbs == null) {
            throw Exceptions.formatHeaderException("生成表头失败,类：[{}]缺少@TbField注解", clazz.getSimpleName());
        }
        TableSetting tableSetting = new TableSetting();
        tableSetting.setTitle(Objects.equals("", tbs.title()) ? null : tbs.title())
                .setCellEditable(tbs.cellEditable())
                .setCheckbox(tbs.checkbox())
                .setViewType(tbs.viewType());
        headerData.setSetting(tableSetting);
    }

    @SuppressWarnings("unchecked")
    private static void callbackHandle(Class clazz, TableHeaderResponseData headerData) throws HeaderException {
        //回调
        try {
            if (BaseTb.class.isAssignableFrom(clazz)) {
                Object o = clazz.getDeclaredConstructor().newInstance();
                BaseTb baseTb = (BaseTb) o;
                baseTb.headerCallback(headerData);
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw Exceptions.formatHeaderException(e, "生成表头失败,获取类:[{}]的无参构造器失败", clazz.getSimpleName());
        }
    }
}
