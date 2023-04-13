package com.iscas.biz.mp.service.common;

import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.mp.mapper.TableMapMapper;
import com.iscas.biz.mp.model.DynamicSql;
import com.iscas.common.tools.core.reflect.ReflectUtils;
import com.iscas.templet.exception.Exceptions;
import com.iscas.templet.exception.ValidDataException;
import com.iscas.templet.view.table.*;
import com.iscas.templet.view.validator.Rule;
import com.iscas.templet.view.validator.RuleCallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 表格通用操作工具类，使用Templet前后台交互协议
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/26 16:48
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "rawtypes"})
@Service
@Slf4j
@Transactional(rollbackFor = Throwable.class)
@ConditionalOnMybatis
public class TableService extends BaseTableService {
    @Autowired
    private TableMapMapper tableMapMapper;

    /**
     * 根据TableSearchRequest 生成一个动态SQL
     *
     * @param request            {@link TableSearchRequest} 前端发送的查询条件
     * @param tableName          表名
     * @param extendSqlCondition 扩展SQL名
     * @return java.lang.String
     * @date 2018/8/27
     * @since jdk1.8
     */
    public String dynamicSql(TableSearchRequest<Map<String, List>> request, String tableName, String extendSqlCondition) {

        //构建SQL
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ").append(tableName);
        sb.append(" WHERE 1 = 1 ");

        //编写WHERE 后的条件
        String afterWhereSql = getAfterWhereSql(request, extendSqlCondition);
        sb.append(afterWhereSql);
        String sql = sb.toString();
        log.debug("SQL:" + sql);
        return sql;
    }

    public String dynamicCountSql(TableSearchRequest<Map<String, List>> request, String tableName, String extendSqlCondition) {
        //构建SQL
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(*) AS COUNT FROM ").append(tableName);
        sb.append(" WHERE 1 = 1 ");

        //编写WHERE 后的条件
        String afterWhereSql = getAfterWhereSql(request, extendSqlCondition);
        sb.append(afterWhereSql);
        String sql = sb.toString();
        log.debug("SQL:" + sql);
        return sql;
    }


    public <T> String dynamicSql(TableSearchRequest<Map<String, List>> request, Class<T> tClass, String extendSqlCondition) {
        return dynamicSql(request, tClass.getSimpleName(), extendSqlCondition);
    }

    public List<Map> dynamicDataResult(TableSearchRequest<Map<String, List>> request, String tableName, String extendSqlCondition) {
        String sql = dynamicSql(request, tableName, extendSqlCondition);
        return tableMapMapper.dynamicSelect(new DynamicSql(sql));
    }

    public long dynamicDataCount(TableSearchRequest<Map<String, List>> request, String tableName, String extendSqlCondition) {
        String sql = dynamicCountSql(request, tableName, extendSqlCondition);
        List<Map> countMaps = tableMapMapper.dynamicSelect(new DynamicSql(sql));
        long count = 0;
        if (countMaps != null && countMaps.size() > 0) {
            count = (long) countMaps.get(0).get("COUNT");
        }
        return count;
    }

    public TableResponse dynamicResponse(TableSearchRequest<Map<String, List>> request, String tableName, String extendSqlCondition) {
        TableResponse tableResponse = new TableResponse();
        TableResponseData<Map> tableResponseData = new TableResponseData<>();
        long count = dynamicDataCount(request, tableName, extendSqlCondition);
        List<Map> data = dynamicDataResult(request, tableName, extendSqlCondition);
        tableResponseData.setData(data);
        Integer pageSize = request.getPageSize();
//        Long rows = count%pageSize == 0 ? count/pageSize : count/pageSize + 1;
        Long rows = count;
        tableResponseData.setRows(rows);
        tableResponse.setValue(tableResponseData);
        return tableResponse;
    }


    public List<Field> getSaveFields(Object saveData, String tableName, TableHeaderResponseData headerData, boolean superClassProps) {
        if (saveData == null) {
            throw Exceptions.runtimeException("保存的数据不能为空");
        }
        if (headerData == null) {
            throw Exceptions.runtimeException("表头不能为空");
        }
        return ReflectUtils.getDistinctFields(saveData.getClass());
    }


    /**
     * <p>传入带保存的实体，表名，表头信息 保存这个实体到数据库，并按表头配置作校验，
     * 返回受影响的条数<p/>
     *
     * @param saveData        带保存的实体数据
     * @param tableName       表名
     * @param headerData      表头信息
     * @param superClassProps 实体是否读取其父类的属性
     * @param ruleCallback    校验规则回掉函数，可以在这里自定义校验，比如校验重复等
     * @return java.lang.Object 主键
     * @throws Exception 异常
     * @date 2018/9/6
     * @since jdk1.8
     */
    public int save(Object saveData, String tableName, TableHeaderResponseData headerData
            , boolean superClassProps, RuleCallback ruleCallback) throws Exception {
        int result;
        //校验
        validateProps(saveData, headerData, ruleCallback);
        //获取保存SQL
        String sql = getSaveSql(saveData, tableName, headerData, superClassProps);
        result = tableMapMapper.dynamicInsert(new DynamicSql(sql));
        return result;
    }


    /**
     * <p>传入带保存的实体，表名，表头信息 保存这个实体到数据库，并按表头配置作校验，
     * 返回插入数据的主键
     * 这里返回主键仅支持MYSQL<p/>
     *
     * @param saveData        带保存的实体数据
     * @param tableName       表名
     * @param headerData      表头信息
     * @param superClassProps 实体是否读取其父类的属性
     * @param ruleCallback    校验规则回掉函数，可以在这里自定义校验，比如校验重复等
     * @return java.lang.Object 主键
     * @throws Exception 异常
     * @date 2018/9/6
     * @since jdk1.8
     */
    @Transactional(rollbackFor = Exception.class)
    public Object saveForMysql(Object saveData, String tableName, TableHeaderResponseData headerData
            , boolean superClassProps, RuleCallback ruleCallback) throws Exception {
        Object result;

        //校验
        validateProps(saveData, headerData, ruleCallback);
        //获取保存SQL
        String sql = getSaveSql(saveData, tableName, headerData, superClassProps);
        int row = tableMapMapper.dynamicInsert(new DynamicSql(sql));
        String getIdSql = "SELECT LAST_INSERT_ID() as id";
        List<Map> ids = tableMapMapper.dynamicSelect(new DynamicSql(getIdSql));
        Map map = ids.get(0);
        Object idObj = map.get("id");
        if (idObj instanceof BigInteger bigInteger) {
            result = bigInteger.intValue();
        } else {
            result = idObj;
        }
        return result;
    }

    /**
     * 校验
     *
     * @param saveData     带保存的实体数据
     *                     //     * @param tableName 表名
     * @param headerData   表头信息
     *                     //     * @param superClassProps 实体是否读取其父类的属性
     * @param ruleCallback 校验规则回掉函数，可以在这里自定义校验，比如校验重复等
     * @throws Exception 异常
     * @date 2018/9/6
     * @since jdk1.8
     */
    public void validateProps(Object saveData, TableHeaderResponseData headerData
            , RuleCallback ruleCallback) throws Exception {

        if (saveData == null) {
            throw Exceptions.runtimeException("保存的数据不能为空");
        }
        if (headerData == null) {
            throw Exceptions.runtimeException("表头不能为空");
        }
        List<TableField> tableFields = headerData.getCols();
        List<Map<String, Object>> serverValidateList = new ArrayList<>();

        for (TableField tableField : tableFields) {
            if (tableField.getRule() != null) {
                Rule rule = tableField.getRule();
                if (rule != null) {
                    Object value;
                    if (saveData instanceof Map saveDataMap) {
                        value = saveDataMap.get(tableField.getField());
                    } else {
                        value = ReflectUtils.invokeGet(saveData, tableField.getField());
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
                    //校验回调函数
                    if (ruleCallback != null) {
                        ruleCallback.validate(saveData, tableField);
                    }
                }
            }
        }
    }

    /**
     * 获取保存的SQL
     *
     * @param saveData        带保存的实体数据
     * @param tableName       表名
     * @param headerData      表头信息
     * @param superClassProps 实体是否读取其父类的属性
     * @return java.lang.String
     * @throws IllegalAccessException IllegalAccessException
     * @date 2018/9/6
     * @since jdk1.8
     */
    public String getSaveSql(Object saveData, String tableName, TableHeaderResponseData headerData, boolean superClassProps) throws IllegalAccessException {

        String sql;
        List<Field> fields = getSaveFields(saveData, tableName, headerData, superClassProps);
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(tableName).append(" ( ");
        for (Field field : fields) {
            Object obj = ReflectUtils.getValue(field, saveData);
            if (obj != null) {
                sb.append(field.getName()).append(" ,");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" ) ").append(" VALUES( ");
        //构建插入SQL
        for (Field field : fields) {
            Object obj = ReflectUtils.getValue(field, saveData);
            if (obj != null) {
                sb.append("'");
                sb.append(obj);
                sb.append("',");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");
        sql = sb.toString();
        log.debug("insert sql:" + sql);
        return sql;
    }

    public String getUpdateSql(Object saveData, String tableName, TableHeaderResponseData headerData, boolean superClassProps) throws IllegalAccessException {

        String sql;
        List<Field> fields = getSaveFields(saveData, tableName, headerData, superClassProps);
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(tableName).append(" SET ");
        for (Field field : fields) {
            Object obj = ReflectUtils.getValue(field, saveData);
            if (obj != null) {
                sb.append(field.getName()).append(" ,");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" ) ").append(" VALUES( ");
        //构建插入SQL
        for (Field field : fields) {
            Object obj = ReflectUtils.getValue(field, saveData);
            if (obj != null) {
                sb.append("'");
                sb.append(obj);
                sb.append("',");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");
        sql = sb.toString();
        log.debug("insert sql:" + sql);
        return sql;
    }
}
