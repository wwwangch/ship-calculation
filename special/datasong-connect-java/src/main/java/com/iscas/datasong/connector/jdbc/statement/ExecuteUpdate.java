package com.iscas.datasong.connector.jdbc.statement;

import com.iscas.datasong.client.DataSongDataService;
import com.iscas.datasong.client.DataSongHttpClient;
import com.iscas.datasong.connector.exception.DatasongClientException;
import com.iscas.datasong.connector.jdbc.ConnectionImpl;
import com.iscas.datasong.connector.parser.SelectSqlParser;
import com.iscas.datasong.connector.util.CollectionUtils;
import com.iscas.datasong.lib.common.DataSongException;
import com.iscas.datasong.lib.request.CreatePullDataRequest;
import com.iscas.datasong.lib.request.search.condition.search.BoolSearchCondition;
import com.iscas.datasong.lib.util.DataSongJsonUtils;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.update.Update;
import net.sf.jsqlparser.statement.update.UpdateSet;

import java.util.*;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/4 19:02
 * @since jdk11
 */
@SuppressWarnings("SingleStatementInBlock")
public class ExecuteUpdate {

    public static int execute(Update update, ConnectionImpl connection, int fetchSize) throws JSQLParserException, DataSongException, DatasongClientException {
        Table table = update.getTable();
        String tableName = table.getName();
        String dbName = connection.getDbName();
        DataSongHttpClient dsHttpClient = connection.getDsHttpClient();
        DataSongDataService dataService = dsHttpClient.getDataService();
        ArrayList<UpdateSet> updateSets = update.getUpdateSets();
        if (CollectionUtils.isNotEmpty(updateSets)) {
            Map<String, Object> updates = getUpdates(updateSets);
            Expression where = update.getWhere();
            BoolSearchCondition boolSearchCondition = new BoolSearchCondition();
            if (!Objects.isNull(where)) {
                // 跟查询一样，解析where条件，生成查询条件
                SelectSqlParser.createSearchCondition(boolSearchCondition, where, true);
            }
            // 拉取数据并修改数据
            return pullAndUpdateData(fetchSize, tableName, dbName, dataService, boolSearchCondition, updates);
        }
        return 0;
    }

    @SuppressWarnings({"unchecked", "LoopStatementThatDoesntLoop"})
    private static int pullAndUpdateData(int fetchSize, String tableName, String dbName, DataSongDataService dataService,
                                          BoolSearchCondition boolSearchCondition, Map<String, Object> updates) throws DataSongException {
        // 构建拉取数据条件
        CreatePullDataRequest createPullDataRequest = new CreatePullDataRequest();
        createPullDataRequest.setSearch(boolSearchCondition);
        createPullDataRequest.batchSize(fetchSize);
        // 获取拉取ID
        String pullId = dataService.createPullData(dbName, tableName, createPullDataRequest);
        int updateCount = 0;
        while (true) {
            String result = dataService.pullData(pullId);
            if (result != null) {
                List<Map<String, Object>> updateData = DataSongJsonUtils.fromJson(result, List.class);
                // 将修改的值替换为数据库里查询出来的值
                updateData.forEach(searchDatum -> searchDatum.putAll(updates));
                // 跟insert一样调用batchSavaData方法，updateData中有_id会修改值
                dataService.batchSaveData(dbName, tableName, updateData);
                updateCount = updateCount + updateData.size();
            } {
                break;
            }
        }
        return updateCount;
    }

    private static Map<String, Object> getUpdates(ArrayList<UpdateSet> updateSets) throws DatasongClientException {
        Map<String, Object> updates = new HashMap<>();
        for (UpdateSet updateSet : updateSets) {
            ArrayList<Column> columns = updateSet.getColumns();
            ArrayList<Expression> expressions = updateSet.getExpressions();
            Column column = columns.get(0);
            String columnName = column.getColumnName();
            if (Objects.equals("_id", columnName)) {
                throw new DatasongClientException("字段:_id不允许修改");
            }
            Expression expression = expressions.get(0);
            Object data = SelectSqlParser.getData(expression);
            updates.put(columnName, data);
        }
        return updates;
    }

}
