package com.iscas.datasong.connector.jdbc.statement;

import com.iscas.datasong.client.DataSongDataService;
import com.iscas.datasong.client.DataSongHttpClient;
import com.iscas.datasong.connector.exception.DatasongClientException;
import com.iscas.datasong.connector.jdbc.ConnectionImpl;
import com.iscas.datasong.connector.parser.SelectSqlParser;
import com.iscas.datasong.connector.util.CollectionUtils;
import com.iscas.datasong.lib.common.DataSongException;
import com.iscas.datasong.lib.request.SearchDataRequest;
import com.iscas.datasong.lib.response.data.SearchDataResponse;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.expression.operators.relational.MultiExpressionList;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.insert.Insert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/4 19:02
 * @since jdk11
 */
public class ExecuteInsert {

    public static List<String> execute(Insert insert, ConnectionImpl connection) throws JSQLParserException, DataSongException, DatasongClientException {
        Table table = insert.getTable();
        String tableName = table.getName();
        String dbName = connection.getDbName();
        List<Column> columns = insert.getColumns();
        List<String> keys = null;
        DataSongHttpClient dsHttpClient = connection.getDsHttpClient();
        DataSongDataService dataService = dsHttpClient.getDataService();
        if (columns == null) {
            // 如果是全查，去查一下表信息
            SearchDataRequest request = new SearchDataRequest();
            request.setSize(0);
            SearchDataResponse searchDataResponse = dataService.search(dbName, tableName, request);
            List<com.iscas.datasong.lib.common.column.Column> headers = searchDataResponse.getHeaders();
            if (CollectionUtils.isNotEmpty(headers)) {
                keys = headers.stream().map(com.iscas.datasong.lib.common.column.Column::getName).toList();
            }
        } else {
            keys = columns.stream().map(Column::getColumnName).toList();
        }
        ItemsList itemsList = insert.getItemsList();
        List<List<Object>> data = new ArrayList<>();
        if (itemsList instanceof ExpressionList) {
            ExpressionList expressionList = (ExpressionList) itemsList;
            List<Object> dataFromExp = getDataFromExp(expressionList, keys);
            data.add(dataFromExp);
        } else if (itemsList instanceof MultiExpressionList) {
            MultiExpressionList multiExpressionList = (MultiExpressionList) itemsList;
            List<ExpressionList> expressionLists = multiExpressionList.getExpressionLists();
            for (ExpressionList expressionList : expressionLists) {
                List<Object> dataFromExp = getDataFromExp(expressionList, keys);
                data.add(dataFromExp);
            }
        } else {
            throw new DatasongClientException(String.format("暂不支持的表达式类型:[%s]", itemsList.getClass().getName()));
        }
        List<Map<String, Object>> insertData = new ArrayList<>();
        for (List<Object> datum : data) {
            Map<String, Object> map = new HashMap<>(16);
            for (int i = 0; i < datum.size(); i++) {
                assert keys != null;
                map.put(keys.get(i), datum.get(i));
            }
            insertData.add(map);
        }
        return dataService.batchSaveData(dbName, tableName, insertData);
    }

    private static List<Object> getDataFromExp(ExpressionList expressionList, List<String> keys) throws DatasongClientException {
        List<Expression> expressions = expressionList.getExpressions();
        List<Object> list = new ArrayList<>();
        for (Expression expression : expressions) {
            list.add(SelectSqlParser.getData(expression));
        }
        if (keys.size() != list.size()) {
            throw new DatasongClientException("插入列的长度与数据的长度不匹配");
        }
        return list;
    }
}
