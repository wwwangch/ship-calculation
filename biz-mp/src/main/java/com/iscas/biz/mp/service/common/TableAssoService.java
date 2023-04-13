package com.iscas.biz.mp.service.common;

import com.iscas.biz.mp.aop.associate.*;
import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.mp.mapper.TableMapMapper;
import com.iscas.biz.mp.model.DynamicSql;
import com.iscas.templet.view.table.TableResponse;
import com.iscas.templet.view.table.TableResponseData;
import com.iscas.templet.view.table.TableSearchRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 多表关系操作工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/26 16:48
 * @since jdk1.8
 */
@SuppressWarnings({"DeprecatedIsStillUsed", "unused", "InfiniteRecursion", "rawtypes", "unchecked"})
@Service
@Slf4j
@Transactional(rollbackFor = Throwable.class)
@Deprecated
@ConditionalOnMybatis
public class TableAssoService extends BaseTableService {
    @Autowired
    private TableMapMapper tableAssoMapper;

    public TableResponse getTableReponse(TableSearchRequest tableSearchRequest) {
        return this.getTableReponse(tableSearchRequest);
    }

    /**
     * 解析多表关联注解，按照前台发送的查询条件，返回TableResponse
     *
     * @param tableSearchRequest 前后台交互协议定义的查询条件
     * @param extendSqlCondition 扩展SQL条件
     * @return com.iscas.templet.view.table.TableResponse
     * @date 2018/9/5
     * @since jdk1.8
     */
    public TableResponse getTableReponse(TableSearchRequest tableSearchRequest, String extendSqlCondition) {

        TableResponse tableResponse = new TableResponse();
        TableResponseData<Map> tableResponseData = new TableResponseData<>();

        List<Map> datas = getSearchData(tableSearchRequest, extendSqlCondition);
        tableResponseData.setData(datas);
        long count = getSearchCount(tableSearchRequest, extendSqlCondition);
        Integer pageSize = tableSearchRequest.getPageSize();
//        Long rows = count%pageSize == 0 ? count/pageSize : count/pageSize + 1;
        Long rows = count;
        tableResponseData.setRows(rows);
        tableResponse.setValue(tableResponseData);
        return tableResponse;
    }

    public List<Map> getSearchData(TableSearchRequest tableSearchRequest) {
        return this.getSearchData(tableSearchRequest);
    }

    /**
     * 解析多表关联注解，按照前台发送的查询条件，返回查询数据List<MapResponseData>
     *
     * @param tableSearchRequest tableSearchRequest 前后台交互协议定义的查询条件
     * @param extendSqlCondition 扩展SQL条件
     * @return java.util.List<java.util.MapResponseData>
     * @date 2018/9/5
     * @since jdk1.8
     */
    public List<Map> getSearchData(TableSearchRequest tableSearchRequest, String extendSqlCondition) {
        String sql = createSearchSql(tableSearchRequest, extendSqlCondition);
        return tableAssoMapper.dynamicSelect(new DynamicSql(sql));
    }

    public long getSearchCount(TableSearchRequest tableSearchRequest) {
        return this.getSearchCount(tableSearchRequest);
    }

    /**
     * 解析多表关联注解，按照前台发送的查询条件，返回查询数据总条目
     *
     * @param tableSearchRequest tableSearchRequest 前后台交互协议定义的查询条件
     * @param extendSqlCondition 扩展SQL条件
     * @return long
     * @date 2018/9/5
     * @since jdk1.8
     */
    public long getSearchCount(TableSearchRequest tableSearchRequest, String extendSqlCondition) {

        String countSql = createCountSql(tableSearchRequest, extendSqlCondition);
        List<Map> countsMap = tableAssoMapper.dynamicSelect(new DynamicSql(countSql));
        long count = 0;
        if (countsMap != null && countsMap.size() > 0) {
            count = (long) countsMap.get(0).get("COUNT");
        }
        return count;
    }

    public String createCountSql(TableSearchRequest tableSearchRequest) {
        return this.createCountSql(tableSearchRequest);
    }

    public String createCountSql(TableSearchRequest tableSearchRequest, String extendSqlCondition) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(*) as COUNT ");
        sb.append(" FROM ");
        //构建from后的条件
        String afterFrom = getAfterFromSql();
        sb.append(afterFrom);

        sb.append(" WHERE 1 = 1 ");

        //编写WHERE 后的条件
        String afterWhereSql = getAfterWhereSql(tableSearchRequest, extendSqlCondition);
        sb.append(afterWhereSql);

        return sb.toString();
    }

    public String createSearchSql(TableSearchRequest tableSearchRequest) {
        return this.createSearchSql(tableSearchRequest, null);
    }

    public String createSearchSql(TableSearchRequest tableSearchRequest, String extendSqlCondition) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        CustomAssociates customAssociates = AssoThreadLocal.ASSOCIATES_THREAD_LOCAL.get();
        CustomAssociate[] associates = customAssociates.associates();
        AssociateTable[] associateTables = customAssociates.associateTables();
        CustomResult[] results = customAssociates.results();

        //构建FROM前的查询条件
        //如果resuts为空使用select * ,否则使用select 各个属性
        if (results == null || results.length == 0) {
            sb.append(" * ");
        } else {
            for (CustomResult result : results) {
                String columnAlias = result.alias();
                String column = result.column();
                String table = result.table();
                //查找result里与table配置匹配上的对应配置,如果Table上没配置别名直接使用table本身的
                //名字作为别名，
                for (AssociateTable assoTable : associateTables) {
                    String name = assoTable.name();
                    String alias = assoTable.alias();
                    if (table.equals(name)) {
                        if (alias != null && !"".equals(alias)) {
                            sb.append(alias).append(".").append(column);
                        } else {
                            sb.append(name).append(".").append(column);
                        }
                        if (columnAlias != null && !"".equals(columnAlias)) {
                            sb.append(" AS ").append(columnAlias);
                        }
                        sb.append(" ,");
                        break;
                    }
                }
            }
            sb.deleteCharAt(sb.length() - 1);
        }

        sb.append(" FROM ");
        //构建from后的条件
        String afterFrom = getAfterFromSql();
        sb.append(afterFrom);

        sb.append(" WHERE 1 = 1 ");

        //编写WHERE 后的条件
        String afterWhereSql = getAfterWhereSql(tableSearchRequest, extendSqlCondition);
        sb.append(afterWhereSql);


        return sb.toString();
    }

    private String getAfterFromSql() {
        StringBuilder sb = new StringBuilder();
        CustomAssociates customAssociates = AssoThreadLocal.ASSOCIATES_THREAD_LOCAL.get();
        CustomAssociate[] associates = customAssociates.associates();
        AssociateTable[] associateTables = customAssociates.associateTables();
        CustomResult[] results = customAssociates.results();
        //构建FROM后的表连接和ON条件
        //将右连接反转成左俩姐，做一个排序，让最左边的最前面
        List<Map<String, String>> assoList = new ArrayList<>();
        for (CustomAssociate asso : associates) {
            Map<String, String> assoMap = new HashMap<>(8);
            if (CustomAssociateType.RIGHT.equals(asso.associateType())) {
                assoMap.put("table1", asso.table2());
                assoMap.put("table1Col", asso.table2Col());
                assoMap.put("table2", asso.table1());
                assoMap.put("table2Col", asso.table1Col());
                assoMap.put("type", "LEFT");
            } else {
                assoMap.put("table2", asso.table2());
                assoMap.put("table2Col", asso.table2Col());
                assoMap.put("table1", asso.table1());
                assoMap.put("table1Col", asso.table1Col());
                assoMap.put("type", asso.associateType().toString());
            }
            assoList.add(assoMap);
        }
        //noinspection ComparatorMethodParameterNotUsed
        assoList = assoList.stream().sorted((map1, map2) -> {
            String table1 = map1.get("table1");
            String table2 = map2.get("table2");
            if (table1.equals(table2)) {
                return 1;
            } else {
                return -1;
            }
        }).toList();
//        MapResponseData<String, String> usedTables = new HashMap<>();
        int i = 0;
        for (Map<String, String> assoMap : assoList) {
            String table1 = assoMap.get("table1");
            String table1Col = assoMap.get("table1Col");
            String table2 = assoMap.get("table2");
            String table2Col = assoMap.get("table2Col");
            String type = assoMap.get("type");
            if (i == 0) {
                sb.append(table1);
            }
            sb.append(" ").append(type);
            sb.append(" JOIN ");
            sb.append(table2);
            sb.append(" ON ");
            sb.append(table1).append(".").append(table1Col);
            sb.append(" = ");
            sb.append(table2).append(".").append(table2Col);
            i++;
        }
        return sb.toString();
    }


}
