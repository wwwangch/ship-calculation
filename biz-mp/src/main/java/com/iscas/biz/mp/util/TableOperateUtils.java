package com.iscas.biz.mp.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.iscas.templet.exception.Exceptions;
import com.iscas.templet.exception.HeaderException;
import com.iscas.templet.exception.ValidDataException;
import com.iscas.templet.helper.HeaderHelper;
import com.iscas.templet.view.table.*;
import com.iscas.templet.view.validator.Rule;
import com.iscas.templet.view.validator.RuleCallback;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/12/29 16:12
 * @since jdk1.8
 */
public class TableOperateUtils {
    private TableOperateUtils() {
    }

    /**
     * 获取表头（实体类中必须有对应的表头注解）
     *
     * @param clazz 实体类
     * @return com.iscas.templet.view.table.TableHeaderResponse
     * @date 2022/12/29
     * @since jdk1.8
     */
    public static TableHeaderResponse getHeader(Class<?> clazz) throws HeaderException {
        TableHeaderResponseData tableHeaderResponseData = HeaderHelper.convertToHeader(clazz);
        TableHeaderResponse tableHeaderResponse = new TableHeaderResponse();
        tableHeaderResponse.setValue(tableHeaderResponseData);
        return tableHeaderResponse;
    }

    /**
     * 新增数据
     *
     * @param service      mybatis-plus的service
     * @param t            实体数据
     * @param ruleCallback 需要特殊处理的一些校验的回调
     * @date 2022/12/29
     * @since jdk11
     */
    public static <T> void add(IService<T> service, T t, RuleCallback ruleCallback) throws ValidDataException, HeaderException {
        TableHeaderResponse header = getHeader(t.getClass());
        validateProps(t, header.getValue(), ruleCallback, service, true);
        service.save(t);
    }


    /**
     * 修改数据
     *
     * @param service      mybatis-plus的service
     * @param t            实体数据
     * @param ruleCallback 需要特殊处理的一些校验的回调
     * @date 2022/12/29
     * @since jdk11
     */
    public static <T> void edit(IService<T> service, T t, RuleCallback ruleCallback) throws ValidDataException, HeaderException {
        TableHeaderResponse header = getHeader(t.getClass());
        validateProps(t, header.getValue(), ruleCallback, service, false);
        service.updateById(t);
    }

    /**
     * 校验某个属性是否重复
     *
     * @param service mybatis-plus的service
     * @param field   属性
     * @param value   值
     * @date 2022/12/29
     * @since jdk1.8
     */
    public static <T> void duplicateProp(IService<T> service, String field, Object value) throws ValidDataException {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(field);
        queryWrapper.eq(field, value);
        long count = service.count(queryWrapper);
        if (count > 0) {
            throw new ValidDataException(String.format("[%s]重复", value == null ? "null" : value.toString()));
        }
    }

    /**
     * 校验各个属性
     *
     * @param saveData      带保存的实体数据
     * @param headerData    表头信息
     * @param ruleCallback  校验规则回掉函数，可以在这里自定义校验，比如校验重复等
     * @param service       mybatis-plus的service
     * @param validDistinct 是否校验distinct为true的字段
     * @throws ValidDataException 校验异常
     * @since jdk1.8
     */
    public static <T> void validateProps(T saveData, TableHeaderResponseData headerData,
                                         RuleCallback ruleCallback, IService<T> service, boolean validDistinct) throws ValidDataException {
        if (saveData == null) {
            throw Exceptions.runtimeException("保存的数据不能为空");
        }
        if (headerData == null) {
            throw Exceptions.runtimeException("表头不能为空");
        }
        List<TableField> tableFields = headerData.getCols();

        for (TableField tableField : tableFields) {
            if (tableField.getRule() != null) {
                Rule rule = tableField.getRule();
                if (rule != null) {
                    Object value;
                    if (saveData instanceof Map) {
                        Map saveDataMap = (Map) saveData;
                        value = saveDataMap.get(tableField.getField());
                    } else {
                        value = ReflectUtil.getFieldValue(saveData, tableField.getField());
                    }

                    if (rule.isRequired() && ObjectUtils.isEmpty(value)) {
                        //必须有值
                        throw Exceptions.validDataException(tableField.getHeader() + "不能为空");
                    }
                    if (rule.getReg() != null && !ObjectUtils.isEmpty(value)
                            && !String.valueOf(value).matches(rule.getReg())) {
                        //正则表达式校验
                        throw Exceptions.validDataException(tableField.getHeader() + "格式校验未通过");
                    }
                    if (rule.getLength() != null && !ObjectUtils.isEmpty(value)) {
                        //长度校验
                        Map<String, Integer> lengthMap = rule.getLength();
                        if (lengthMap.containsKey("min")) {
                            if (String.valueOf(value).length() < lengthMap.get("min")) {
                                throw Exceptions.validDataException(tableField.getHeader() + "长度不得小于" + lengthMap.get("min"));
                            }
                            if (String.valueOf(value).length() > lengthMap.get("max")) {
                                throw Exceptions.validDataException(tableField.getHeader() + "长度不得大于" + lengthMap.get("max"));
                            }
                        }
                    }
                    if (ArrayUtils.isNotEmpty(rule.getMustIn())) {
                        // 值必须在数组中
                        if (Objects.isNull(value) || !ArrayUtils.contains(rule.getMustIn(), value.toString())) {
                            throw Exceptions.formatValidDataException("[" + tableField.getHeader() + "]值必须在:{}中", Arrays.toString(rule.getMustIn()));
                        }
                    }
                    if (ArrayUtils.isNotEmpty(rule.getMustNotIn())) {
                        // 值必须不在数组中
                        if (Objects.nonNull(value) && ArrayUtils.contains(rule.getMustNotIn(), value.toString())) {
                            throw Exceptions.formatValidDataException("[" + tableField.getHeader() + "]值必须不在:{}中", Arrays.toString(rule.getMustIn()));
                        }
                    }
                    if (rule.getHighVal() != null && !ObjectUtils.isEmpty(value)) {
                        // 校验最大值
                        int compare = value.toString().compareTo(rule.getHighVal().toString());
                        if (rule.isContainsHigh() && compare > 0) {
                            throw Exceptions.formatValidDataException("[{}]值必须小于等于:[{}]", tableField.getHeader(), rule.getHighVal());
                        } else if (!rule.isContainsHigh() && compare >= 0) {
                            throw Exceptions.formatValidDataException("[{}]值必须小于:[{}]", tableField.getHeader(), rule.getHighVal());
                        }
                    }
                    if (rule.getLowVal() != null && !ObjectUtils.isEmpty(value)) {
                        // 校验最小值
                        int compare = value.toString().compareTo(rule.getLowVal().toString());
                        if (rule.isContainsLow() && compare < 0) {
                            throw Exceptions.formatValidDataException("[{}]值必须大于等于:[{}]", tableField.getHeader(), rule.getLowVal());
                        } else if (!rule.isContainsLow() && compare <= 0) {
                            throw Exceptions.formatValidDataException("[{}]值必须大于:[{}]", tableField.getHeader(), rule.getLowVal());
                        }
                    }
                    if (validDistinct && rule.isDistinct()) {
                        // 如果需要校验重复性，校验是否数据库中已有该属性
                        duplicateProp(service, tableField.getField(), value);
                    }
                }
            }
            //校验回调函数
            if (ruleCallback != null) {
                ruleCallback.validate(saveData, tableField);
            }
        }
    }

    /**
     * 批量删除
     *
     * @param service mybatis-plus的service
     * @param ids     ID集合
     * @date 2022/12/29
     * @since jdk1.8
     */
    public static <T> void batchDelete(IService<T> service, List<? extends Serializable> ids) {
        service.removeByIds(ids);
    }

    /**
     * 删除
     *
     * @param service mybatis-plus的service
     * @param id      id
     * @date 2022/12/30
     * @since jdk1.8
     */
    public static <T> void delete(IService<T> service, Serializable id) {
        service.removeById(id);
    }

    /**
     * 通用查询，返回表格结构
     *
     * @param service mybatis-plus的service
     * @param request 查询条件
     * @date 2022/12/30
     * @since jdk1.8
     */
    public static <T> TableResponse<T> search(IService<T> service, TableSearchRequest request, Class<T> clazz) throws HeaderException, ValidDataException {
        // 分页查询数据
        IPage<T> page = searchData(service, request, clazz);
        // 构建表格返回结构
        TableResponse<T> response = new TableResponse<>();
        TableResponseData<T> data = new TableResponseData<>();
        data.setRows(page.getTotal());
        data.setData(page.getRecords());
        response.setValue(data);
        return response;
    }

    /**
     * 通用查询，返回数据
     *
     * @param service mybatis-plus的service
     * @param request 查询条件
     * @date 2022/12/30
     * @since jdk1.8
     */
    public static <T> IPage<T> searchData(IService<T> service, TableSearchRequest request, Class<T> clazz) throws HeaderException, ValidDataException {
        // 构建查询条件
        QueryWrapper<T> queryWrapper = checkAndBuildQueryWrapper(request, clazz);
        // 构建分页、排序条件，并执行查询
        Integer pageNumber = request.getPageNumber();
        Integer pageSize = request.getPageSize();
        String sortField = request.getSortField();
        TableSortType sortOrder = request.getSortOrder();
        Page page = Page.of(pageNumber, pageSize);
        if (sortField != null) {
            OrderItem orderItem = sortOrder == null || TableSortType.asc == sortOrder ? OrderItem.asc(sortField) : OrderItem.desc(sortField);
            page.setOrders(List.of(orderItem));
        }
        return service.page(page, queryWrapper);
    }

    /**
     * 获取EXCEL的表头
     *
     * @param excelIgnoredFields 忽略的属性
     * @param clazz              类
     * @return java.util.LinkedHashMap<java.lang.String, java.lang.String>
     * @date 2022/12/30
     * @since jdk1.8
     */
    public static LinkedHashMap<String, String> getExcelHeader(String[] excelIgnoredFields, Class clazz) throws HeaderException {
        TableHeaderResponse header = TableOperateUtils.getHeader(clazz);
        List<TableField> cols = header.getValue().getCols();
        return cols.stream()
                .filter(col -> !ArrayUtils.contains(excelIgnoredFields, col.getField()))
                .collect(Collectors.toMap(TableField::getField, TableField::getHeader, (s, s2) -> null, LinkedHashMap::new));
    }

    private static <T> QueryWrapper<T> checkAndBuildQueryWrapper(TableSearchRequest request, Class<T> clazz) throws HeaderException, ValidDataException {
        TableHeaderResponse header = getHeader(clazz);
        TableHeaderResponseData headerValue = header.getValue();
        List<TableField> cols = headerValue.getCols();
        // 将列信息转为Map，方便使用
        Map<String, TableField> colMap = cols.stream().collect(Collectors.toMap(TableField::getField, tf -> tf));
        Map<String, List> filter = (Map<String, List>) request.getFilter();
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        if (MapUtil.isNotEmpty(filter)) {
            for (Map.Entry<String, List> entry : filter.entrySet()) {
                String field = entry.getKey();
                List conditions = entry.getValue();
                if (CollectionUtil.isEmpty(conditions)) {
                    throw new ValidDataException(String.format("字段:[%s]的查询条件不能为空", field));
                }
                if (!colMap.containsKey(field)) {
                    throw new ValidDataException(String.format("字段:[%s]未在表头中定义", field));
                }
                TableField tableField = colMap.get(field);
                if (!tableField.isSearch()) {
                    throw new ValidDataException(String.format("字段:[%s]不支持查询", field));
                }
                TableSearchType searchType = tableField.getSearchType();
                if (searchType == null) {
                    throw new ValidDataException(String.format("字段:[%s]为定义searchType", field));
                }
                switch (searchType) {
                    case exact:
                        queryWrapper.eq(field, conditions.get(0));
                        break;
                    case like:
                        queryWrapper.like(field, conditions.get(0));
                        break;
                    case prefix:
                        queryWrapper.likeRight(field, conditions.get(0));
                        break;
                    case range:
                        if (conditions.size() < 2) {
                            throw new ValidDataException(String.format("字段:[%s]的searchType为range，必须指定一个范围条件", field));
                        }
                        Object from = conditions.get(0);
                        Object to = conditions.get(1);
                        if (Objects.isNull(from) && Objects.isNull(to)) {
                            throw new ValidDataException(String.format("字段:[%s]的searchType为range，两个范围条件不能都为空", field));
                        } else if (Objects.isNull(from)) {
                            queryWrapper.le(field, to);
                        } else if (Objects.isNull(to)) {
                            queryWrapper.ge(field, from);
                        } else {
                            queryWrapper.between(field, from, to);
                        }
                        break;
                    default:
                        break;

                }
            }
        }
        return queryWrapper;
    }

    public static <T> void filterToEntity(TableSearchRequest request, T t) throws ValidDataException, HeaderException {
        Map<String, List> filter = (Map<String, List>) request.getFilter();
        TableHeaderResponse header = TableOperateUtils.getHeader(t.getClass());
        Map<String, TableField> colMap = header.getValue().getCols().stream().collect(Collectors.toMap(TableField::getField, tf -> tf));
        if (MapUtil.isNotEmpty(filter)) {
            for (Map.Entry<String, List> entry : filter.entrySet()) {
                String field = entry.getKey();
                List conditions = entry.getValue();
                if (CollectionUtil.isEmpty(conditions)) {
                    throw new ValidDataException(String.format("字段:[%s]的查询条件不能为空", field));
                }
                if (!colMap.containsKey(field)) {
                    throw new ValidDataException(String.format("字段:[%s]未在表头中定义", field));
                }
                TableField tableField = colMap.get(field);
                if (!tableField.isSearch()) {
                    throw new ValidDataException(String.format("字段:[%s]不支持查询", field));
                }
                TableSearchType searchType = tableField.getSearchType();
                if (searchType == null) {
                    throw new ValidDataException(String.format("字段:[%s]为定义searchType", field));
                }
                // 暂时不考虑时间的多个值的问题
                ReflectUtil.setFieldValue(t, field, conditions.get(0));
            }
        }
    }

}
