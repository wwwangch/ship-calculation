package com.iscas.biz.mp.table.service;


import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.CaseFormat;
import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.mp.config.db.TableDefinitionSqlCreatorConfig;
import com.iscas.biz.mp.table.mapper.TableDefinitionMapper;
import com.iscas.biz.mp.table.model.ColumnDefinition;
import com.iscas.biz.mp.table.model.TableDefinition;
import com.iscas.biz.mp.table.model.TableDefinitionConfig;
import com.iscas.biz.mp.util.IdGeneratorUtils;
import com.iscas.common.tools.core.reflect.ReflectUtils;
import com.iscas.common.tools.core.string.StringRaiseUtils;
import com.iscas.common.web.tools.json.JsonUtils;
import com.iscas.templet.common.ResponseEntity;
import com.iscas.templet.exception.BaseException;
import com.iscas.templet.exception.Exceptions;
import com.iscas.templet.exception.ValidDataException;
import com.iscas.templet.view.table.*;
import com.iscas.templet.view.validator.Rule;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 表格自定义操作服务
 *
 * @author DataDong
 * @date Create in 2018/9/11 14:24
 */
@SuppressWarnings({"deprecation", "AlibabaLowerCamelCaseVariableNaming", "AliDeprecation", "AlibabaMethodTooLong", "rawtypes", "unchecked", "unused"})
@Service
@Slf4j
@ConditionalOnMybatis
public class TableDefinitionService {

    @Autowired
    private TableDefinitionMapper tableDefinitionMapper;

    @Autowired
    private TableDefinitionConfig tableDefinitionConfig;

    /**
     * 表从mysql导入神州通用数据库后有些字段多了很多空格，trim处理一下
     */
    private void trimColumnDefinitions(List<ColumnDefinition> columnDefinitions) {
        if (CollectionUtils.isNotEmpty(columnDefinitions)) {
            columnDefinitions.forEach(this::trimStrOfObj);
        }
    }

    /**
     * 表从mysql导入神州通用数据库后有些字段多了很多空格，trim处理一下
     */
    private void trimStrOfObj(Object obj) {
        ReflectUtils.getDistinctFields(obj.getClass()).stream()
                .filter(field -> field.getType() == String.class)
                .forEach(field -> {
                    try {
                        Object o = ReflectUtils.getValue(field, obj);
                        if (o != null) {
                            ReflectUtils.setValue(field, obj, o.toString().trim());
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
    }

    /**
     * 获取表格的表头定义，不带下拉列表选项
     *
     * @param tableIdentity tableIdentity
     * @param withOption    withOption
     * @return TableHeaderResponse
     */
    @SuppressWarnings("UnusedAssignment")
    private TableHeaderResponse getTableHeader(String tableIdentity, boolean withOption) throws BaseException {
        TableHeaderResponse tableHeaderResponse = new TableHeaderResponse();
        long start = System.currentTimeMillis();
        try {
            if (ObjectUtils.isEmpty(tableIdentity)) {
                throw Exceptions.baseException("传入表格标识参数为空", "parameter is empty or null");
            }

            List<ColumnDefinition> columnDefinitions =
                    tableDefinitionMapper.getHeaderByIdentify(tableDefinitionConfig.getHeaderDefinitionTableName(), tableIdentity);
            trimColumnDefinitions(columnDefinitions);
            if (columnDefinitions == null || columnDefinitions.size() == 0) {
                throw Exceptions.baseException(String.format("[%s]的表头定义不存在！", tableIdentity), String.format("column definition not exist for [%s]", tableIdentity));
            }

            //添加表格定义
            TableDefinition tableDefinition =
                    tableDefinitionMapper.getTableByIdentify(tableDefinitionConfig.getTableDefinitionTableName(), tableIdentity);
            trimStrOfObj(tableDefinition);
            String primaryKey = tableDefinition.getPrimaryKey();
            if (StringUtils.isEmpty(primaryKey)) {
                primaryKey = tableDefinitionConfig.getPrimaryKey();
                if (StringUtils.isEmpty(primaryKey)) {
                    primaryKey = "id";
                }
            }

            //下划线转小驼峰
//            columnDefinitions.forEach(columnDefinition -> columnDefinition.setField(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnDefinition.getField())));

            TableHeaderResponseData tableHeaderResponseData = new TableHeaderResponseData();
            tableHeaderResponseData.setCols(analyzeTableField(columnDefinitions, withOption));

            TableSetting tableSetting = convertToTableSetting(tableDefinition);
            tableHeaderResponseData.setSetting(tableSetting);
            tableHeaderResponse.setValue(tableHeaderResponseData);
        } catch (Exception e) {
            throw Exceptions.baseException("获取表头失败", e);
        } finally {
            tableHeaderResponse.setTookInMillis(System.currentTimeMillis() - start);
        }
        return tableHeaderResponse;
    }


    private TableSetting convertToTableSetting(TableDefinition tableDefinition) {
        TableSetting tableSetting = new TableSetting();
        tableSetting.setTitle(tableDefinition.getTitle());
        tableSetting.setCheckbox(tableDefinition.getCheckbox());
        tableSetting.setBackInfo(tableDefinition.getBackInfo());
        tableSetting.setFrontInfo(tableDefinition.getFrontInfo());
        //增加判断，修复了传入值为空时异常的BUG
        tableSetting.setViewType((tableDefinition.getViewType() == null || tableDefinition.getViewType().isBlank()) ? null : TableViewType.valueOf(tableDefinition.getViewType()));

        //构建buttonSetting
        String buttonSetting = tableDefinition.getButtonSetting();
        if (StringUtils.isNotEmpty(buttonSetting)) {
            TypeReference<List<ButtonSetting>> typeReference = new TypeReference<>() {
            };
            List<ButtonSetting> buttonSettings = JsonUtils.fromJson(buttonSetting, typeReference);
            tableSetting.setButtonSetting(buttonSettings);
        }
        return tableSetting;
    }

    /**
     * 将数据表ColumnDefinition转换成控件模型TableField
     *
     * @param columnDefinitions columnDefinitions
     * @return List<TableField>
     */
    private List<TableField> analyzeTableField(List<ColumnDefinition> columnDefinitions, boolean withOption)
            throws ValidDataException {
        List<TableField> tableFields = new ArrayList<>();
        for (ColumnDefinition columnDefinition : columnDefinitions) {
            TableField tableField = new TableField();
            tableField.setAddable(columnDefinition.isAddable());
            tableField.setEditable(columnDefinition.isEditable());
            //字段转为驼峰
//            tableField.setField(StringRaiseUtils.convertToHump(columnDefinition.getField()));
            tableField.setField(columnDefinition.getField());
            tableField.setHeader(columnDefinition.getHeader());
            tableField.setHidden(columnDefinition.isHidden());
            tableField.setLink(columnDefinition.isLink());
            tableField.setSelectUrl(columnDefinition.getSelectUrl());

            //rule
            Rule rule = new Rule();
            rule.setDistinct(columnDefinition.isDistinct());
            rule.setReg(columnDefinition.getReg());
            rule.setRequired(columnDefinition.isRequired());
            if (columnDefinition.getMaxLength() != null || columnDefinition.getMinLength() != null) {
                Map<String, Integer> map = new HashMap<>(2);
                if (columnDefinition.getMaxLength() != null) {
                    map.put("max", columnDefinition.getMaxLength());
                }
                if (columnDefinition.getMinLength() != null) {
                    map.put("min", columnDefinition.getMinLength());
                }
                rule.setLength(map);
            }
            tableField.setRule(rule);

            tableField.setSearch(columnDefinition.isSearch());
            tableField.setSearchType(TableSearchType.analyzeSearchType(columnDefinition.getSearchType()));
            String type = columnDefinition.getType();
            tableField.setType(TableFieldType.analyzeFieldType(type));
            tableField.setSortable(columnDefinition.isSortable());

            //构建Option选项
            if (withOption) {
                Map<Object, Object> valueMap = null;
                if (!StringUtils.isEmpty(columnDefinition.getRefValue())) {
                    valueMap = JsonUtils.fromJson(columnDefinition.getRefValue(), new TypeReference<HashMap>() {
                    });
                } else if (!StringUtils.isEmpty(columnDefinition.getRefTable()) &&
                        !StringUtils.isEmpty(columnDefinition.getRefTable())) {
                    List<Map<Object, Object>> refValues = tableDefinitionMapper.getRefTable(columnDefinition.getRefTable(),
                            "id", columnDefinition.getRefColumn());
                    valueMap = new LinkedHashMap<>();
                    for (Map<Object, Object> tmpItem : refValues) {
                        valueMap.put(tmpItem.get("id"), tmpItem.get("value"));
                    }
                }

                if (valueMap != null) {
                    List<ComboboxData> comboboxDataList = new ArrayList<>();
                    for (Map.Entry<Object, Object> entry : valueMap.entrySet()) {
                        ComboboxData comboboxData = new ComboboxData();
                        comboboxData.setLabel(entry.getValue().toString());
                        comboboxData.setValue(entry.getKey());
                        comboboxDataList.add(comboboxData);
                    }
                    tableField.setOption(comboboxDataList);
                    //检查列的type和ref是否匹配
                    if (!(TableFieldType.select.name().equalsIgnoreCase(type.trim()) || TableFieldType.selectCanedit.name().equalsIgnoreCase(type.trim()))) {
                        ValidDataException validDataException = new ValidDataException(
                                String.format("列[%s]的type配置不是[select],但是存在ref配置！", columnDefinition.getField()));
                        validDataException.setMsgDetail(
                                String.format("the type of field [%s] is not [select],but exist ref config", columnDefinition.getField()));
                        throw validDataException;
                    }
                }
            }
            tableFields.add(tableField);
        }

        return tableFields;
    }


    /**
     * 获取表头定义
     *
     * @param tableIdentity 前后台统一的表格标识
     * @return TableHeaderResponse
     */
    public TableHeaderResponse getTableHeader(String tableIdentity) throws BaseException {
        log.info("获取{}的表头", tableIdentity);
        return getTableHeader(tableIdentity, false);
    }

    /**
     * 获取表头定义，带下拉选项
     *
     * @param tableIdentity 前后台统一的表格标识
     * @return TableHeaderResponse
     */
    public TableHeaderResponse getHeaderWithOption(String tableIdentity) throws BaseException {
        return getTableHeader(tableIdentity, true);
    }

    public TableHeaderResponse getHeaderWithOption(Class clazz) throws BaseException {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(clazz);
        String tableName = tableInfo.getTableName();
        return getTableHeader(tableName, true);
    }


    public <T> TableResponse<T> getData(String tableIdentity, TableSearchRequest request, Map<String, Object> dynamicParam,
                                        String dynamicSql) throws ValidDataException {
        TableResponse tableResponse = new TableResponse();
        long start = System.currentTimeMillis();
        try {
            //查询表格定义
            TableDefinition tableDefinition =
                    tableDefinitionMapper.getTableByIdentify(tableDefinitionConfig.getTableDefinitionTableName(), tableIdentity);
            trimStrOfObj(tableDefinition);
            List<ColumnDefinition> columnDefinitions =
                    tableDefinitionMapper.getHeaderByIdentify(tableDefinitionConfig.getHeaderDefinitionTableName(), tableIdentity);
            trimColumnDefinitions(columnDefinitions);
            //noinspection ConstantConditions
            if (null == tableDefinition) {
                ValidDataException validDataException = new ValidDataException(String.format("[%s]的表格定义不存在！", tableIdentity));
                validDataException.setMsgDetail(String.format("table definition not exist for [%s]", tableIdentity));
                throw validDataException;
            }

            //解析前台参数
            if (request == null) {
                request = new TableSearchRequest();
            }
            Map<String, Object> paramMap = (Map<String, Object>) request.getFilter();
            StringBuilder where = new StringBuilder();
            if (paramMap == null) {
                paramMap = new HashMap<>(4);
            }
            Map<String, Object> tmpMap = new HashMap<>(4);
            for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
                //查询条件为空的忽略
                if (entry.getValue() == null) {
                    continue;
                }
                //检查条件是否为List
                if (!(entry.getValue() instanceof List)) {
                    ValidDataException validDataException = new ValidDataException(String.format("[%s]列的搜索条件取值不正确！", entry.getKey()));
                    validDataException.setMsgDetail(String.format("invalidate value [%s] for field [%s]", entry.getValue(), entry.getKey()));
                    throw validDataException;
                }
                List values = (List) entry.getValue();
                //参数为空忽略
                if (values.size() == 0) {
                    continue;
                }

                //查找对应的列定义
                ColumnDefinition columnDefinition = null;
                for (ColumnDefinition tmp : columnDefinitions) {
                    if (entry.getKey().equalsIgnoreCase(tmp.getField())) {
                        if (!tmp.isSearch()) {
                            ValidDataException validDataException = new ValidDataException(
                                    String.format("[%s]列的属性search!=true，不允许检索！", tmp.getField()));
                            validDataException.setMsgDetail(String.format("search!=true for field [%s]", tmp.getField()));
                            throw validDataException;
                        }
                        columnDefinition = tmp;
                        break;
                    }
                }

                //是否找到列定义
                if (null == columnDefinition) {
                    ValidDataException validDataException = new ValidDataException(
                            String.format("找不到[%s]列的定义！", entry.getKey()));
                    validDataException.setMsgDetail(String.format("can not find definition for field [%s]", entry.getKey()));
                    throw validDataException;
                }

                //解析searchType
                TableSearchType searchType = TableSearchType.analyzeSearchType(columnDefinition.getSearchType());
                if (searchType == null) {
                    ValidDataException validDataException = new ValidDataException(
                            String.format("[%s]列的searchType属性配置信息错误！", columnDefinition.getField()));
                    validDataException.setMsgDetail(String
                            .format("invalidate search type config [%s] for field [%s]", columnDefinition.getSearchType(),
                                    columnDefinition.getField()));
                    throw validDataException;
                }

                switch (searchType) {
                    case exact:
                        if (ObjectUtils.isEmpty(values.get(0))) {
                            continue;
                        }
                        tmpMap.put(entry.getKey(), values.get(0));
                        if (where.length() > 0) {
                            where.append(" AND ");
                        }
                        where.append(String.format(" %s=#{param.%s}", entry.getKey(), entry.getKey()));
                        break;
                    case like:
                        if (ObjectUtils.isEmpty(values.get(0))) {
                            continue;
                        }
                        tmpMap.put(entry.getKey(), "%" + values.get(0) + "%");
                        if (where.length() > 0) {
                            where.append(" AND ");
                        }
                        where.append(String.format(" %s LIKE #{param.%s}", entry.getKey(), entry.getKey()));
                        break;
                    case prefix:
                        if (ObjectUtils.isEmpty(values.get(0))) {
                            continue;
                        }
                        tmpMap.put(entry.getKey(), values.get(0) + "%");
                        if (where.length() > 0) {
                            where.append(" AND ");
                        }
                        where.append(String.format(" %s LIKE #{param.%s}", entry.getKey(), entry.getKey()));
                        break;
                    case range:
                        if (values.get(0) != null && values.get(1) != null) {
                            tmpMap.put(entry.getKey() + "Min", values.get(0));
                            tmpMap.put(entry.getKey() + "Max", values.get(1));
                            if (where.length() > 0) {
                                where.append(" AND ");
                            }
                            where.append(String.format(" %s>=#{param.%s} AND %s<=#{param.%s}", entry.getKey(),
                                    entry.getKey() + "Min", entry.getKey(), entry.getKey() + "Max"));
                        } else if (values.get(0) != null) {
                            tmpMap.put(entry.getKey() + "Min", values.get(0));
                            if (where.length() > 0) {
                                where.append(" AND ");
                            }
                            where.append(String.format(" %s>=#{param.%s}", entry.getKey(), entry.getKey() + "Min"));
                        } else if (values.get(1) != null) {
                            tmpMap.put(entry.getKey() + "Max", values.get(1));
                            if (where.length() > 0) {
                                where.append(" AND ");
                            }
                            where.append(String.format(" %s<=#{param.%s}", entry.getKey(), entry.getKey() + "Max"));
                        } else {
                            continue;
                        }
                        break;
                    default:
                        break;
                }
            }
            //合并参数
            paramMap.putAll(tmpMap);

            //处理动态参数
            if (dynamicParam != null) {
                paramMap.putAll(dynamicParam);
            }

            //拼接不带分页sql
            String sql = tableDefinition.getSql().replace("{", "{param.");
            String countSql, dataSql;

            //构建下拉列表查询条件
            Map<String, List> optionsFilter = request.getOptionsFilter();
            StringBuilder orCondition = new StringBuilder();
            if (MapUtils.isNotEmpty(optionsFilter)) {
                int[] ix = new int[1];
                optionsFilter.forEach((field, datas) -> {
                    StringBuilder sb = new StringBuilder();
                    sb.append(" ").append(field).append(" IN ").append(" ( ");
                    for (int i = 0; i < datas.size(); i++) {
                        Object o = datas.get(i);
                        Object data;
                        if (o instanceof String) {
                            data = "'" + o + "'";
                        } else {
                            data = o;
                        }
                        if (i == 0) {
                            sb.append(data);
                        } else {
                            sb.append(" , ").append(data);
                        }
                    }
                    sb.append(" ) ");
                    if (ix[0] == 0) {
                        orCondition.append(sb);
                    } else {
                        ix[0]++;
                        orCondition.append(" AND ").append(sb);
                    }
                });
            }
            if (orCondition.length() != 0) {
                if (where.length() == 0) {
                    where.append(orCondition);
                } else {
                    where.append(" AND ").append(orCondition);
                }
            }

            //添加动态sql add by zqw 2021-02-22
            if (StringUtils.isNotEmpty(dynamicSql)) {
                if (where.length() == 0) {
                    where.append(dynamicSql);
                } else {
                    where.append(" AND ").append(dynamicSql);
                }
            }

            if (where.length() != 0) {
                if (MapUtils.isNotEmpty(dynamicParam)) {
                    for (Map.Entry<String, Object> entry : dynamicParam.entrySet()) {
                        where.append(" AND ").append(entry.getKey()).append(" = ")
                                .append("'").append(entry.getValue()).append("'");
                    }
                }
                countSql = "SELECT COUNT(1) FROM (" + sql + ") t WHERE " + where;
                dataSql = "SELECT * FROM (" + sql + ") t WHERE " + where;
            } else {
                if (MapUtils.isNotEmpty(dynamicParam)) {
                    where.append(" WHERE 1=1 ");
                    for (Map.Entry<String, Object> entry : dynamicParam.entrySet()) {
                        where.append(" AND ").append(entry.getKey()).append(" = ")
                                .append("'").append(entry.getValue()).append("'");
                    }
                }
                countSql = "SELECT COUNT(1) FROM (" + sql + ") t " + where;
                dataSql = sql + where;
            }

            //查询count
            TableResponseData tableResponseData = new TableResponseData();
            log.debug("count sql : " + countSql);
            int count = tableDefinitionMapper.getCountBySql(countSql, paramMap);
            tableResponseData.setRows((long) count);

            //查询数据
            if (count > (request.getPageSize() * (request.getPageNumber() - 1))) {
                if (!StringUtils.isEmpty(request.getSortField())) {
                    dataSql = String.format(dataSql + " ORDER BY %s %s ", request.getSortField(), request.getSortOrder().name().toUpperCase());
                }
                dataSql = switch (TableDefinitionSqlCreatorConfig.mode) {
                    case "mysql", "oscar" ->
                            String.format(dataSql + " LIMIT %d,%d;", (request.getPageSize() * (request.getPageNumber() - 1)),
                                    request.getPageSize());
                    case "oracle" -> {
                        String str = "SELECT * FROM (SELECT ROWNUM AS rowno, t.*\n" +
                                "\n" +
                                "\tFROM (@sql@) t\n" +
                                "\n" +
                                "         WHERE ROWNUM <= @end@) table_alias\n" +
                                "\n" +
                                " WHERE table_alias.rowno >= @start@";
                        int s = (request.getPageNumber() - 1) * request.getPageSize() + 1;
                        int e = request.getPageNumber() * request.getPageSize();
                        yield str.replace("@sql@", dataSql)
                                .replace("@start@", String.valueOf(s))
                                .replace("@end@", String.valueOf(e));
                    }
                    default ->
                            throw Exceptions.unsupportedOperationException("不支持的数据源类型:[" + TableDefinitionSqlCreatorConfig.mode + "]");
                };
                log.debug("get data sql : " + sql);
                List<Map<String, Object>> datas = tableDefinitionMapper.getDataBySql(dataSql, paramMap);
                tableResponseData.setData(datas);
            }
            tableResponse.setValue(tableResponseData);
        } catch (BaseException e) {
            e.printStackTrace();
            throw e;
        } finally {
            tableResponse.setTookInMillis(System.currentTimeMillis() - start);
        }
        return tableResponse;
    }

    /**
     * 查询时驼峰转为下划线，返回结果时下划线转为驼峰
     */
    public <T> TableResponse<T> getData(Class clazz, TableSearchRequest request, Map<String, Object> dynamicParam,
                                        String dynamicSql) throws ValidDataException {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(clazz);
        String tableIdentity = tableInfo.getTableName();

        TableResponse tableResponse = new TableResponse();
        long start = System.currentTimeMillis();
        try {
            //查询表格定义
            TableDefinition tableDefinition =
                    tableDefinitionMapper.getTableByIdentify(tableDefinitionConfig.getTableDefinitionTableName(), tableIdentity);
            trimStrOfObj(tableDefinition);
            List<ColumnDefinition> columnDefinitions =
                    tableDefinitionMapper.getHeaderByIdentify(tableDefinitionConfig.getHeaderDefinitionTableName(), tableIdentity);
            trimColumnDefinitions(columnDefinitions);
            //noinspection ConstantConditions
            if (null == tableDefinition) {
                ValidDataException validDataException = new ValidDataException(String.format("[%s]的表格定义不存在！", tableIdentity));
                validDataException.setMsgDetail(String.format("table definition not exist for [%s]", tableIdentity));
                throw validDataException;
            }

            //解析前台参数
            if (request == null) {
                request = new TableSearchRequest();
            }
            Map<String, Object> paramMap = (Map<String, Object>) request.getFilter();
            StringBuilder where = new StringBuilder();
            if (paramMap == null) {
                paramMap = new HashMap<>(4);
            }
            Map<String, Object> tmpMap = new HashMap<>(4);
            for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
                //查询条件为空的忽略
                if (entry.getValue() == null) {
                    continue;
                }
                //转下划线
                String key = StringRaiseUtils.convertToUnderline(entry.getKey());

                //检查条件是否为List
                if (!(entry.getValue() instanceof List)) {
                    ValidDataException validDataException = new ValidDataException(String.format("[%s]列的搜索条件取值不正确！", key));
                    validDataException.setMsgDetail(String.format("invalidate value [%s] for field [%s]", entry.getValue(), key));
                    throw validDataException;
                }
                List values = (List) entry.getValue();
                //参数为空忽略
                if (values.size() == 0) {
                    continue;
                }

                //查找对应的列定义
                ColumnDefinition columnDefinition = null;
                for (ColumnDefinition tmp : columnDefinitions) {
                    if (key.equalsIgnoreCase(tmp.getField())) {
                        if (!tmp.isSearch()) {
                            ValidDataException validDataException = new ValidDataException(
                                    String.format("[%s]列的属性search!=true，不允许检索！", tmp.getField()));
                            validDataException.setMsgDetail(String.format("search!=true for field [%s]", tmp.getField()));
                            throw validDataException;
                        }
                        columnDefinition = tmp;
                        break;
                    }
                }

                //是否找到列定义
                if (null == columnDefinition) {
                    ValidDataException validDataException = new ValidDataException(
                            String.format("找不到[%s]列的定义！", key));
                    validDataException.setMsgDetail(String.format("can not find definition for field [%s]", key));
                    throw validDataException;
                }

                //解析searchType
                TableSearchType searchType = null;
                Map<String, TableSearchType> searchTypeMap = (Map<String, TableSearchType>) request.getSearchType();
                if (searchTypeMap != null) {
                    searchType = searchTypeMap.get(entry.getKey());
                }
                if (searchType == null) {
                    searchType = TableSearchType.analyzeSearchType(columnDefinition.getSearchType());
                }

                if (searchType == null) {
                    ValidDataException validDataException = new ValidDataException(
                            String.format("[%s]列的searchType属性配置信息错误！", columnDefinition.getField()));
                    validDataException.setMsgDetail(String
                            .format("invalidate search type config [%s] for field [%s]", columnDefinition.getSearchType(),
                                    columnDefinition.getField()));
                    throw validDataException;
                }

                switch (searchType) {
                    case exact:
                        if (ObjectUtils.isEmpty(values.get(0))) {
                            continue;
                        }
                        tmpMap.put(key, values.get(0));
                        if (where.length() > 0) {
                            where.append(" AND ");
                        }
                        where.append(String.format(" %s=#{param.%s}", key, key));
                        break;
                    case like:
                        if (ObjectUtils.isEmpty(values.get(0))) {
                            continue;
                        }
                        tmpMap.put(key, "%" + values.get(0) + "%");
                        if (where.length() > 0) {
                            where.append(" AND ");
                        }
                        where.append(String.format(" %s LIKE #{param.%s}", key, key));
                        break;
                    case prefix:
                        if (ObjectUtils.isEmpty(values.get(0))) {
                            continue;
                        }
                        tmpMap.put(key, values.get(0) + "%");
                        if (where.length() > 0) {
                            where.append(" AND ");
                        }
                        where.append(String.format(" %s LIKE #{param.%s}", key, key));
                        break;
                    case range:
                        if (values.get(0) != null && values.get(1) != null) {
                            tmpMap.put(key + "Min", values.get(0));
                            tmpMap.put(key + "Max", values.get(1));
                            if (where.length() > 0) {
                                where.append(" AND ");
                            }
                            where.append(String.format(" %s>=#{param.%s} AND %s<=#{param.%s}", key,
                                    key + "Min", key, key + "Max"));
                        } else if (values.get(0) != null) {
                            tmpMap.put(key + "Min", values.get(0));
                            if (where.length() > 0) {
                                where.append(" AND ");
                            }
                            where.append(String.format(" %s>=#{param.%s}", key, key + "Min"));
                        } else if (values.get(1) != null) {
                            tmpMap.put(key + "Max", values.get(1));
                            if (where.length() > 0) {
                                where.append(" AND ");
                            }
                            where.append(String.format(" %s<=#{param.%s}", key, key + "Max"));
                        } else {
                            continue;
                        }
                        break;
                    default:
                        break;
                }
            }
            //合并参数
            paramMap.putAll(tmpMap);

            //处理动态参数
            if (dynamicParam != null) {
                for (Map.Entry<String, Object> entry : dynamicParam.entrySet()) {
                    //转下划线
                    paramMap.put(StringRaiseUtils.convertToUnderline(entry.getKey()), entry.getValue());
                }
            }

            //拼接不带分页sql
            String sql = tableDefinition.getSql().replace("{", "{param.");
            String countSql, dataSql;

            //构建下拉列表查询条件
            Map<String, List> optionsFilter = request.getOptionsFilter();
            StringBuilder orCondition = new StringBuilder();
            if (MapUtils.isNotEmpty(optionsFilter)) {
                int[] ix = new int[1];
                optionsFilter.forEach((field, datas) -> {
                    StringBuilder sb = new StringBuilder();
                    sb.append(" ").append(field).append(" IN ").append(" ( ");
                    for (int i = 0; i < datas.size(); i++) {
                        Object o = datas.get(i);
                        Object data;
                        if (o instanceof String) {
                            data = "'" + o + "'";
                        } else {
                            data = o;
                        }
                        if (i == 0) {
                            sb.append(data);
                        } else {
                            sb.append(" , ").append(data);
                        }
                    }
                    sb.append(" ) ");
                    if (ix[0] == 0) {
                        orCondition.append(sb);
                    } else {
                        ix[0]++;
                        orCondition.append(" AND ").append(sb);
                    }
                });
            }
            if (orCondition.length() != 0) {
                if (where.length() == 0) {
                    where.append(orCondition);
                } else {
                    where.append(" AND ").append(orCondition);
                }
            }

            //添加动态sql add by zqw 2021-02-22
            if (StringUtils.isNotEmpty(dynamicSql)) {
                if (where.length() == 0) {
                    where.append(dynamicSql);
                } else {
                    where.append(" AND ").append(dynamicSql);
                }
            }

            if (where.length() != 0) {
                if (MapUtils.isNotEmpty(dynamicParam)) {
                    for (Map.Entry<String, Object> entry : dynamicParam.entrySet()) {
                        where.append(" AND ").append(entry.getKey()).append(" = ")
                                .append("'").append(entry.getValue()).append("'");
                    }
                }
                countSql = "SELECT COUNT(1) FROM (" + sql + ") t WHERE " + where;
                dataSql = "SELECT * FROM (" + sql + ") t WHERE " + where;
            } else {
                if (MapUtils.isNotEmpty(dynamicParam)) {
                    where.append(" WHERE 1=1 ");
                    for (Map.Entry<String, Object> entry : dynamicParam.entrySet()) {
                        where.append(" AND ").append(entry.getKey()).append(" = ")
                                .append("'").append(entry.getValue()).append("'");
                    }
                }
                countSql = "SELECT COUNT(1) FROM (" + sql + ") t " + where;
                dataSql = sql + where;
            }

            //查询count
            TableResponseData tableResponseData = new TableResponseData();
            log.debug("count sql : " + countSql);
            int count = tableDefinitionMapper.getCountBySql(countSql, paramMap);
            tableResponseData.setRows((long) count);

            //查询数据
            if (count > (request.getPageSize() * (request.getPageNumber() - 1))) {
                if (!StringUtils.isEmpty(request.getSortField())) {
                    dataSql = String.format(dataSql + " ORDER BY %s %s ", request.getSortField(), request.getSortOrder().name().toUpperCase());
                }
                dataSql = switch (TableDefinitionSqlCreatorConfig.mode) {
                    case "mysql", "oscar" ->
                            String.format(dataSql + " LIMIT %d,%d;", (request.getPageSize() * (request.getPageNumber() - 1)),
                                    request.getPageSize());
                    case "oracle" -> {
                        String str = "SELECT * FROM (SELECT ROWNUM AS rowno, t.*\n" +
                                "\n" +
                                "\tFROM (@sql@) t\n" +
                                "\n" +
                                "         WHERE ROWNUM <= @end@) table_alias\n" +
                                "\n" +
                                " WHERE table_alias.rowno >= @start@";
                        int s = (request.getPageNumber() - 1) * request.getPageSize() + 1;
                        int e = request.getPageNumber() * request.getPageSize();
                        yield str.replace("@sql@", dataSql)
                                .replace("@start@", String.valueOf(s))
                                .replace("@end@", String.valueOf(e));
                    }
                    default ->
                            throw Exceptions.unsupportedOperationException("不支持的数据源类型:[" + TableDefinitionSqlCreatorConfig.mode + "]");
                };
                log.debug("get data sql : " + sql);
                List<Map<String, Object>> datas = tableDefinitionMapper.getDataBySql(dataSql, paramMap);
                //下划线转为驼峰
                if (CollectionUtils.isNotEmpty(datas)) {
                    datas = datas.stream().map(map -> map.entrySet().stream().collect(Collectors.toMap(
                            entry -> StringRaiseUtils.convertToHump(entry.getKey()),
                            Map.Entry::getValue,
                            (a, b) -> b,
                            HashMap::new
                    ))).collect(Collectors.toList());
                }
                tableResponseData.setData(datas);
            }
            tableResponse.setValue(tableResponseData);
        } catch (BaseException e) {
            e.printStackTrace();
            throw e;
        } finally {
            tableResponse.setTookInMillis(System.currentTimeMillis() - start);
        }
        return tableResponse;
    }


    /**
     * 获取表格数据，不带表头
     *
     * @param tableIdentity tableIdentity
     * @param request       前台构造的查询条件
     * @param dynamicParam  通过session传入的动态条件
     * @return TableResponse
     */
    public <T> TableResponse<T> getData(String tableIdentity, TableSearchRequest request, Map<String, Object> dynamicParam)
            throws ValidDataException {
        return getData(tableIdentity, request, dynamicParam, null);
    }

    /**
     * 添加批量删除接口 add by zqw 20210221
     */
    public ResponseEntity batchDeleteData(String tableIdentity, List<Object> ids) throws ValidDataException {
        ResponseEntity responseEntity = new ResponseEntity();
        long start = System.currentTimeMillis();
        try {
            //查询表格定义
            TableDefinition tableDefinition = tableDefinitionMapper.getTableByIdentify(tableDefinitionConfig.getTableDefinitionTableName(), tableIdentity);
            trimStrOfObj(tableDefinition);
            //noinspection ConstantConditions
            if (null == tableDefinition) {

                ValidDataException validDataException = new ValidDataException(String.format("[%s]的表格定义不存在！", tableIdentity));
                validDataException.setMsgDetail(String.format("table definition not exist for [%s]", tableIdentity));
                throw validDataException;
            }

            //检查参数
            if (StringUtils.isEmpty(tableDefinition.getTableName())) {
                ValidDataException validDataException = new ValidDataException(String.format("[%s]的表格定义中tableName为空！", tableIdentity));
                validDataException.setMsgDetail(String.format("column tableName [%s] is empty", tableIdentity));
                throw validDataException;
            }

            String primaryKey = tableDefinition.getPrimaryKey();
            if (StringUtils.isEmpty(primaryKey)) {
                primaryKey = tableDefinitionConfig.getPrimaryKey();
                if (StringUtils.isEmpty(primaryKey)) {
                    primaryKey = "id";
                }
            }
            int ret = tableDefinitionMapper.batchDeleteData(tableDefinition.getTableName(), primaryKey, ids);
            responseEntity.setValue(ret);
        } catch (BaseException e) {
            e.printStackTrace();
            throw e;
        } finally {
            responseEntity.setTookInMillis(System.currentTimeMillis() - start);
        }
        return responseEntity;
    }

    /**
     * 弃用{@link #batchDeleteData(String, List)}
     * 删除表格记录
     *
     * @param tableIdentity tableIdentity
     * @param id            id
     * @return ResponseEntity
     */
    @Deprecated
    public ResponseEntity deleteData(String tableIdentity, Object id) throws ValidDataException {
        ResponseEntity responseEntity = new ResponseEntity();
        long start = System.currentTimeMillis();
        try {
            //查询表格定义
            TableDefinition tableDefinition = tableDefinitionMapper.getTableByIdentify(tableDefinitionConfig.getTableDefinitionTableName(), tableIdentity);
            trimStrOfObj(tableDefinition);
            //noinspection ConstantConditions
            if (null == tableDefinition) {
                ValidDataException validDataException = new ValidDataException(String.format("[%s]的表格定义不存在！", tableIdentity));
                validDataException.setMsgDetail(String.format("table definition not exist for [%s]", tableIdentity));
                throw validDataException;
            }

            //检查参数
            if (StringUtils.isEmpty(tableDefinition.getTableName())) {
                ValidDataException validDataException = new ValidDataException(String.format("[%s]的表格定义中tableName为空！", tableIdentity));
                validDataException.setMsgDetail(String.format("column tableName [%s] is empty", tableIdentity));
                throw validDataException;
            }

            String primaryKey = tableDefinition.getPrimaryKey();
            if (StringUtils.isEmpty(primaryKey)) {
                primaryKey = tableDefinitionConfig.getPrimaryKey();
                if (StringUtils.isEmpty(primaryKey)) {
                    primaryKey = "id";
                }
            }
            int ret = tableDefinitionMapper.deleteData(tableDefinition.getTableName(), primaryKey, id);
            responseEntity.setValue(ret);
        } catch (BaseException e) {
            e.printStackTrace();
            throw e;
        } finally {
            responseEntity.setTookInMillis(System.currentTimeMillis() - start);
        }
        return responseEntity;
    }

    /**
     * 实体类驼峰转下划线
     */
    public ResponseEntity saveData(Class clazz, Object item, boolean add, Map<String, Object> forceItem) throws ValidDataException {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(clazz);
        String tableName = tableInfo.getTableName();
        List<TableFieldInfo> fieldList = tableInfo.getFieldList();
        Map params = new HashMap(8);
        fieldList.forEach(tableFieldInfo -> {
            Field field = tableFieldInfo.getField();
            try {
                Object v = ReflectUtils.getValue(field, item);
                if (v != null && StringUtils.isNotBlank(v.toString())) {
                    String column = tableFieldInfo.getColumn();
                    params.put(column, v);
                }
            } catch (IllegalAccessException ignored) {
            }
        });

        Object keyValue = ReflectUtil.getFieldValue(item, tableInfo.getKeyProperty());

        if (!ObjectUtils.isEmpty(keyValue)) {
            params.put(tableInfo.getKeyColumn(), keyValue);
        }
        return this.saveData(tableName, params, add, clazz, forceItem);
    }

    public ResponseEntity saveData(String tableIdentity, Map<String, Object> item, boolean add) throws ValidDataException {
        return this.saveData(tableIdentity, item, add, null, null);
    }

    @SuppressWarnings({"AlibabaUndefineMagicConstant", "CaughtExceptionImmediatelyRethrown", "StatementWithEmptyBody"})
    public ResponseEntity saveData(String tableIdentity, Map<String, Object> item, boolean add, Class clazz, Map<String, Object> forceItem) throws ValidDataException {
        ResponseEntity responseEntity = new ResponseEntity();
        long start = System.currentTimeMillis();
        try {
            //查询表格定义
            TableDefinition tableDefinition =
                    tableDefinitionMapper.getTableByIdentify(tableDefinitionConfig.getTableDefinitionTableName(), tableIdentity);
            trimStrOfObj(tableDefinition);
            //noinspection ConstantConditions
            if (null == tableDefinition) {
                ValidDataException validDataException = new ValidDataException(String.format("[%s]的表格定义不存在！", tableIdentity));
                validDataException.setMsgDetail(String.format("table definition not exist for [%s]", tableIdentity));
                throw validDataException;
            }

            //检查参数
            if (StringUtils.isEmpty(tableDefinition.getTableName())) {
                ValidDataException validDataException = new ValidDataException(String.format("[%s]的表格定义中tableName为空！", tableIdentity));
                validDataException.setMsgDetail(String.format("column tableName [%s] is empty", tableIdentity));
                throw validDataException;
            }

            String primaryKey = tableDefinition.getPrimaryKey();
            if (StringUtils.isEmpty(primaryKey)) {
                primaryKey = tableDefinitionConfig.getPrimaryKey();
                if (StringUtils.isEmpty(primaryKey)) {
                    primaryKey = "id";
                }
            }

            List<ColumnDefinition> columnDefinitions = tableDefinitionMapper.getHeaderByIdentify(tableDefinitionConfig.getHeaderDefinitionTableName(), tableIdentity);
            //校验

            //构造sql
            //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
            SQL sql = new SQL();
            sql.INSERT_INTO(tableDefinition.getTableName());

            SQL updateSql = new SQL();
            updateSql.UPDATE(tableDefinition.getTableName());

            for (ColumnDefinition columnDefinition : columnDefinitions) {
                //如果是强制编辑的列，跳过
                if (forceItem != null && forceItem.containsKey(columnDefinition.getField())) {
                    continue;
                }

                if (Objects.equals(primaryKey, columnDefinition.getField())) {
                    if (add) {
                        if (MapUtils.isNotEmpty(item)) {
                            if (!item.containsKey(primaryKey)) {
                                //item中没有主键的值，调用 IdGeneratorUtils 自动生成主键，
                                // 如果返回空，则不处理主键字段，默认为数据库自增
                                String idValue = IdGeneratorUtils.generator(clazz, primaryKey);
                                if (idValue != null) {
                                    item.put(primaryKey, idValue);
                                    // 设置键值
                                    sql.VALUES(columnDefinition.getField(), String.format("#{param.%s}", columnDefinition.getField()));
                                    updateSql.SET(String.format("%s = #{param.%s}", columnDefinition.getField(), columnDefinition.getField()));
                                }
                            } else {
                                //item中有了主键的值
                                // 设置键值
                                sql.VALUES(columnDefinition.getField(), String.format("#{param.%s}", columnDefinition.getField()));
                                updateSql.SET(String.format("%s = #{param.%s}", columnDefinition.getField(), columnDefinition.getField()));
                            }
                        }
                    } else {
                        // 设置键值
                        sql.VALUES(columnDefinition.getField(), String.format("#{param.%s}", columnDefinition.getField()));
                        updateSql.SET(String.format("%s = #{param.%s}", columnDefinition.getField(), columnDefinition.getField()));
                    }
                    continue;
                }
                if (columnDefinition.isAddable() || columnDefinition.isEditable()) {
                    if (item.containsKey(columnDefinition.getField())) {
                        //规则校验
                        Object value = item.get(columnDefinition.getField());
                        String strValue = (value == null ? null : value.toString());
                        //正则检查
                        if (!StringUtils.isEmpty(columnDefinition.getReg())) {
                            if (StringUtils.isNotEmpty(strValue)) {
                                if (!strValue.matches(columnDefinition.getReg())) {
                                    ValidDataException validDataException = new ValidDataException(String.format("[%s]表格的列[%s]取值[%s]不满足正则[%s]要求！",
                                            tableIdentity, columnDefinition.getField(), strValue, columnDefinition.getReg()));
                                    validDataException.setMsgDetail(String.format("the value [%s] of column [%s] for tableName [%s] not meet reg [%s]",
                                            strValue, columnDefinition.getField(), tableIdentity, columnDefinition.getReg()));
                                    throw validDataException;
                                }
                            }
                        }

                        //长度检查
                        if (columnDefinition.getMinLength() > 0) {
                            if (!StringUtils.isEmpty(strValue) && strValue.length() >= columnDefinition.getMinLength()) {
                            } else {
                                ValidDataException validDataException = new ValidDataException(String.format("[%s]表格的列[%s]取值[%s]不满足MinLength[%d]要求！",
                                        tableIdentity, columnDefinition.getField(), strValue, columnDefinition.getMinLength()));
                                validDataException.setMsgDetail(String.format("the value [%s] of column [%s] for tableName [%s] not meet MinLength [%d]",
                                        strValue, columnDefinition.getField(), tableIdentity, columnDefinition.getMinLength()));
                                throw validDataException;
                            }
                        }
                        if (columnDefinition.getMaxLength() > 0) {
                            if (!StringUtils.isEmpty(strValue) && strValue.length() <= columnDefinition.getMaxLength()) {
                            } else {
                                ValidDataException validDataException = new ValidDataException(String.format("[%s]表格的列[%s]取值[%s]不满足MaxLength[%d]要求！",
                                        tableIdentity, columnDefinition.getField(), strValue, columnDefinition.getMinLength()));
                                validDataException.setMsgDetail(String.format("the value [%s] of column [%s] for tableName [%s] not meet MaxLength [%d]",
                                        strValue, columnDefinition.getField(), tableIdentity, columnDefinition.getMinLength()));
                                throw validDataException;
                            }
                        }

                        // 设置键值
                        sql.VALUES(columnDefinition.getField(), String.format("#{param.%s}", columnDefinition.getField()));
                        updateSql.SET(String.format("%s = #{param.%s}", columnDefinition.getField(), columnDefinition.getField()));
                    } else if (columnDefinition.isRequired()) {
                        //必填而没填
                        ValidDataException validDataException = new ValidDataException(String.format("[%s]的表格列[%s]必填！", tableIdentity, columnDefinition.getField()));
                        validDataException.setMsgDetail(String.format("column [%s] for tableName [%s] is empty", columnDefinition.getField(), tableIdentity));
                        throw validDataException;
                    }
                    System.out.println();
                }
                //新增时候校验重复问题
                if (columnDefinition.isAddable() && columnDefinition.isDistinct() && add) {
                    String field = columnDefinition.getField();
                    Object value = item.get(columnDefinition.getField());
                    String selectSQL = tableDefinition.getSql();
                    int count = tableDefinitionMapper.getCountByField(selectSQL, field, value);
                    if (count > 0) {
                        throw Exceptions.formatValidDataException("[{}]的表格列[{}]数据[{}]在数据库中已经存在，不能重复", tableIdentity, field, value);
                    }
                }

            }

            //添加强制修改的参数 add by zqw 2020021
            if (MapUtils.isNotEmpty(forceItem)) {
                for (Map.Entry<String, Object> entry : forceItem.entrySet()) {
                    sql.VALUES(entry.getKey(), String.format("#{param.%s}", entry.getKey()));
                    updateSql.SET(String.format("%s = #{param.%s}", entry.getKey(), entry.getKey()));
                    item.put(entry.getKey(), entry.getValue());
                }
            }


            String sqlString = sql.toString();

            if (add) {
                tableDefinitionMapper.saveData(sqlString, item);
            } else {
                updateSql.WHERE(String.format("%s = #{param.%s}", primaryKey, primaryKey));
                int res = tableDefinitionMapper.updateData(updateSql.toString(), item);
                if (res == 0) {
                    tableDefinitionMapper.saveData(sqlString, item);
                }
            }
            responseEntity.setValue(item.get(primaryKey));
        } catch (BaseException e) {
            throw e;
        } finally {
            responseEntity.setTookInMillis(System.currentTimeMillis() - start);
        }
        return responseEntity;
    }

    /**
     * 获取表头下拉列表
     */
    public ResponseEntity getHeaderCombobox(String tableIdentity, String field, TableSearchRequest request) throws ValidDataException {
        if (request == null) {
            request = new TableSearchRequest();
        }
        ResponseEntity responseEntity = new ResponseEntity();
        request.setPageSize(Integer.MAX_VALUE);
        request.setPageNumber(1);
        request.setOptionsFilter(null);
        TableResponse tableResponse = this.getData(tableIdentity, request, null);
        if (tableResponse != null) {
            TableResponseData<Map> tableResponseData = (TableResponseData<Map>) tableResponse.getValue();
            if (tableResponseData != null) {
                List<Map> maps = tableResponseData.getData();
                if (CollectionUtils.isNotEmpty(maps)) {
                    List<ComboboxData> comboboxDatas = new ArrayList<>();
                    for (Map map : maps) {
                        Object fieldx = map.get(field);
                        if (fieldx != null) {
                            ComboboxData comboboxData = new ComboboxData();
                            comboboxData.setLabel(String.valueOf(fieldx));
                            comboboxData.setValue(fieldx);
                            comboboxDatas.add(comboboxData);
                        }
                    }
                    if (CollectionUtils.isNotEmpty(comboboxDatas)) {
                        comboboxDatas = comboboxDatas.stream().distinct().toList();
                    }
                    responseEntity.setValue(comboboxDatas);
                }
            }
        }
        return responseEntity;
    }

}
