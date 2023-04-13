package com.iscas.datasong.connector.jdbc.statement;

import com.fasterxml.jackson.core.type.TypeReference;
import com.iscas.datasong.client.DataSongDataService;
import com.iscas.datasong.client.DataSongHttpClient;
import com.iscas.datasong.connector.exception.DatasongClientException;
import com.iscas.datasong.connector.jdbc.ConnectionImpl;
import com.iscas.datasong.connector.jdbc.ResultSetImpl;
import com.iscas.datasong.connector.parser.ResultParser;
import com.iscas.datasong.connector.parser.SelectSqlParser;
import com.iscas.datasong.connector.util.CollectionUtils;
import com.iscas.datasong.lib.common.DataItem;
import com.iscas.datasong.lib.common.DataSongException;
import com.iscas.datasong.lib.common.column.Column;
import com.iscas.datasong.lib.request.CreatePullDataRequest;
import com.iscas.datasong.lib.request.SearchDataRequest;
import com.iscas.datasong.lib.response.data.SearchDataResponse;
import com.iscas.datasong.lib.util.DataSongJsonUtils;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.arithmetic.IntegerDivision;
import net.sf.jsqlparser.statement.select.*;
import org.javatuples.Quartet;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/1/7 21:15
 * @since jdk1.8
 */
public class ExecuteQuery {
    private ExecuteQuery() {
    }

    @SuppressWarnings("unchecked")
    public static void execute(ResultSetImpl rs, net.sf.jsqlparser.statement.Statement statement) throws JSQLParserException, SQLException, DataSongException, DatasongClientException {
        ConnectionImpl connection = (ConnectionImpl) rs.getStatement().getConnection();
        String dbName = connection.getDbName();
        DataSongHttpClient dsHttpClient = connection.getDsHttpClient();
        DataSongDataService dataService = dsHttpClient.getDataService();
        if (statement instanceof Select) {
            Quartet<String, SearchDataRequest, List<SelectItem>, Boolean> request = SelectSqlParser.parseSelect((Select) statement);
            SearchDataRequest searchDataRequest = request.getValue1();
            String tableName = request.getValue0();
            if ("DUAL".equalsIgnoreCase(tableName)) {
                // 不需要查询数据库，直接解析函数
                rs.setPullModel(false);
                // 解析结果
                List<Map<String, Object>> searchData = new ArrayList<>();
                Map<String, Object> data = new HashMap<>();
                data.put(null, null);
                searchData.add(data);
                ResultParser.parse(searchData, null, request.getValue2(), request.getValue3());
                searchData.remove(null);
                // 将结果放在ResultSet中
                rs.setCacheData(searchData);
            } else if (searchDataRequest.getStatistic() != null || !Objects.equals(searchDataRequest.getSize(), Integer.MAX_VALUE)) {
                // 对于查询最后都采用拉取的方式，但CreatePullDataRequest中没找到设置分页和设置统计信息的地方，故这两种情况采用查询方式
                rs.setPullModel(false);
                SearchDataResponse searchResult = dataService.search(dbName, tableName, searchDataRequest);
                if (!Objects.isNull(searchResult)) {
                    List<DataItem> items = searchResult.getItems();
                    if (CollectionUtils.isNotEmpty(items)) {
                        List<Map<String, Object>> searchData = items.stream().map(item -> {
                            try {
                                return (Map<String, Object>) DataSongJsonUtils.fromJson(item.getSource(), new TypeReference<Map<String, Object>>() {
                                });
                            } catch (DataSongException e) {
                                // todo 暂时抛出运行时异常
                                throw new RuntimeException(e);
                            }
                        }).toList();
                        // 解析结果
                        ResultParser.parse(searchData, searchResult.getStatistic(), request.getValue2(), request.getValue3());
                        // 将结果放在ResultSet中
                        rs.setCacheData(searchData);
                    }
                }
            } else {
                // 采用拉取方式
                rs.setPullModel(true);
                // 构建拉取条件
                CreatePullDataRequest createPullDataRequest = new CreatePullDataRequest();
                createPullDataRequest.setSearch(searchDataRequest.getSearch());
                createPullDataRequest.setSort(searchDataRequest.getSort());
                createPullDataRequest.setColumns(searchDataRequest.getColumns());
                createPullDataRequest.batchSize(rs.getFetchSize());
                // 获取拉取ID
                String pullId = dataService.createPullData(dbName, tableName, createPullDataRequest);
                if (pullId != null) {
                    // 设置拉取数据的回调，真正触发拉取数据在ResultSet中
                    rs.setPullDataService(() -> {
                        try {
                            String result = dataService.pullData(pullId);
                            if (result != null) {
                                List<Map<String, Object>> searchData = DataSongJsonUtils.fromJson(result, List.class);
                                // 解析结果
                                ResultParser.parse(searchData, null, request.getValue2(), request.getValue3());
                                // 将结果放在ResultSet中，注意这里有线程安全问题，也就是不能 对于同一个ResultSet多线程使用
                                rs.setCacheData(searchData);
                            }
                        } catch (DataSongException e) {
                            // todo 暂时先抛出一个运行时异常
                            throw new RuntimeException(e);
                        }
                    });
                }
            }

            // 获取返回列的索引与列名的映射关系
            Map<Integer, String> headerMapping = getHeaderMapping(dbName, tableName, request.getValue2(), dataService);
            rs.setHeaderMapping(headerMapping);
        }

    }

    private static Map<Integer, String> getHeaderMapping(String dbName, String tableName, List<SelectItem> selectItems,
                                                         DataSongDataService dataService) throws DataSongException, DatasongClientException {
        Map<Integer, String> result = new HashMap<>(16);
        if (CollectionUtils.isNotEmpty(selectItems)) {
            int i = -1;
            List<String> header = null;
            for (SelectItem selectItem : selectItems) {
                if (selectItem instanceof SelectExpressionItem) {
                    i++;
                    SelectExpressionItem selectExpressionItem = (SelectExpressionItem) selectItem;
                    Alias alias = selectExpressionItem.getAlias();
                    if (!Objects.isNull(alias)) {
                        // 如果有别名，直接使用其别名作为映射的值
                        result.put(i, alias.getName());
                    } else {
                        Expression expression = selectExpressionItem.getExpression();
                        if (expression instanceof net.sf.jsqlparser.schema.Column) {
                            // 获取列名作为映射的值
                            result.put(i, ((net.sf.jsqlparser.schema.Column) expression).getColumnName());
                        } else if (expression instanceof Function || expression instanceof IntegerDivision || expression instanceof TimeKeyExpression ||
                                expression instanceof ExtractExpression) {
                            // 函数, 没有使用别名，直接使用其toString作为值
                            result.put(i, expression.toString());
                        } else {
                            throw new DatasongClientException(String.format("暂不支持表达式:[%s]的结果索引映射转化", expression.getClass().getName()));
                        }
                    }
                } else if (selectItem instanceof AllColumns || selectItem instanceof AllTableColumns) {
                    if (header == null) {
                        // AllColumns是全查，获取其所有列
                        header = getHeader(dbName, tableName, dataService);
                    }
                    for (String s : header) {
                        result.put(++i, s);
                    }
                } else {
                    throw new DatasongClientException(String.format("暂不支持selectItem:[%s]的结果索引映射转化", selectItem.getClass().getName()));
                }
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    private static List<String> getHeader(String dbName, String tableName, DataSongDataService dataService) throws DataSongException {
        // todo 暂时通过分页获取第一页查询数据,转化返回的header中的列信息
        SearchDataRequest request = new SearchDataRequest();
        request.setSize(0);
        SearchDataResponse searchDataResponse = dataService.search(dbName, tableName, request);
        if (!Objects.isNull(searchDataResponse)) {
            List<Column> headers = searchDataResponse.getHeaders();
            if (CollectionUtils.isNotEmpty(headers)) {
                // 如果返回头中没有_id，添加上
                boolean hasNo_id = headers.stream().noneMatch(h -> Objects.equals("_id", h.getName()));
                if (hasNo_id) {
                    Column column = new Column();
                    column.setName("_id");
                    headers.add(0, column);
                    return headers.stream().map(Column::getName).toList();
                }
            }
        }
        return Collections.EMPTY_LIST;
    }
}
