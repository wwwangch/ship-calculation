package com.iscas.biz.mp.service.common;

import com.iscas.templet.view.table.TableSearchRequest;
import com.iscas.templet.view.table.TableSearchType;
import com.iscas.templet.view.table.TableSortType;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/9/5 10:22
 * @since jdk1.8
 */
@SuppressWarnings("rawtypes")
public class BaseTableService {
    @SuppressWarnings({"ConstantConditions", "AlibabaMethodTooLong"})
    protected String getAfterWhereSql(TableSearchRequest<Map<String, List>> request, String extendSqlCondition) {
        Integer pageNumber = request.getPageNumber();
        Integer pageSize = request.getPageSize();
        Map<String, List> filter = request.getFilter();
        Map<String, TableSearchType> searchType = request.getSearchType();
        String sortField = request.getSortField();
        TableSortType sortOrder = request.getSortOrder();
        StringBuilder sb = new StringBuilder();
        if (filter != null) {
            for (Map.Entry<String, List> entry : filter.entrySet()) {
                //拼接各个字段查询
                String field = entry.getKey();
                List value = entry.getValue();
                if (CollectionUtils.isNotEmpty(value)) {
                    sb.append(" AND ").append(field);
                    if (searchType != null) {
                        if (searchType.get(field) != null) {
                            //构建查询方式(like prefix 等)
                            switch (searchType.get(field)) {
                                case like -> {
                                    //如果模糊匹配
                                    sb.append(" like '%");
                                    sb.append(value.get(0)).append("%'");
                                }
                                case prefix -> {
                                    //如果是前缀查询
                                    sb.append(" like '");
                                    sb.append(value.get(0)).append("%'");
                                }
                                case range -> {
                                    //如果是范围查询
                                    Object v1 = value.get(0);
                                    Object v2 = value.get(1);
                                    if (v1 == null || Objects.equals("null", v1)) {
                                        sb.append(" < '");
                                        sb.append(v2).append("'");
                                    } else if (v2 == null || Objects.equals("null", v2)) {
                                        sb.append(" > '");
                                        sb.append(v1).append("'");
                                    } else if (v1 != null && !Objects.equals("null", v1)
                                            && v2 != null && !Objects.equals("null", v2)) {
                                        sb.append(" > '");
                                        sb.append(v1).append("'");
                                        sb.append(" AND ");
                                        sb.append(field);
                                        sb.append(" < '");
                                        sb.append(v2).append("'");
                                    }
                                }
                                case exact -> {}
                                default -> {
                                    //如果是精确查询
                                    sb.append(" = '");
                                    sb.append(value.get(0)).append("'");
                                }
                            }
                        }
                    } else {
                        //默认就是精确查询
                        sb.append(" = '");
                        sb.append(value.get(0)).append("'");
                    }
                }
            }
        }

        //扩展SQL条件
        if (extendSqlCondition != null) {
            sb.append(" AND ");
            sb.append(extendSqlCondition);
        }

        //排序
        if (sortField != null) {
            sb.append(" ORDER BY ");
            sb.append(sortField);

            if (sortOrder != null) {
                sb.append(sortOrder.toString().toUpperCase());
            } else {
                sb.append(" ASC ");
            }
        }

        //分页
        int start = (pageNumber - 1) * pageSize;
        int end = pageSize * pageNumber;
        sb.append(" LIMIT ");
        sb.append(start);
        sb.append(" , ");
        sb.append(end);
        return sb.toString();
    }
}
