package com.iscas.datasong.connector.jdbc.statement;

import com.iscas.datasong.client.DataSongDataService;
import com.iscas.datasong.client.DataSongHttpClient;
import com.iscas.datasong.connector.exception.DatasongClientException;
import com.iscas.datasong.connector.jdbc.ConnectionImpl;
import com.iscas.datasong.connector.parser.SelectSqlParser;
import com.iscas.datasong.lib.common.DataSongException;
import com.iscas.datasong.lib.request.CreatePullDataRequest;
import com.iscas.datasong.lib.request.search.condition.search.BoolSearchCondition;
import com.iscas.datasong.lib.util.DataSongJsonUtils;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.delete.Delete;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/4 19:02
 * @since jdk11
 */
@SuppressWarnings("SingleStatementInBlock")
public class ExecuteDelete {

    public static int execute(Delete delete, ConnectionImpl connection, int fetchSize) throws JSQLParserException, DataSongException, DatasongClientException {
        Table table = delete.getTable();
        String tableName = table.getName();
        String dbName = connection.getDbName();
        DataSongHttpClient dsHttpClient = connection.getDsHttpClient();
        DataSongDataService dataService = dsHttpClient.getDataService();
        Expression where = delete.getWhere();
        BoolSearchCondition boolSearchCondition = new BoolSearchCondition();
        if (!Objects.isNull(where)) {
            // 跟查询一样，解析where条件，生成查询条件
            SelectSqlParser.createSearchCondition(boolSearchCondition, where, true);
        }
        // 根据删除条件拉取并删除数据
        return pullAndDeleteData(fetchSize, tableName, dbName, dataService, boolSearchCondition);
    }

    @SuppressWarnings({"unchecked", "LoopStatementThatDoesntLoop"})
    private static int pullAndDeleteData(int fetchSize, String tableName, String dbName, DataSongDataService dataService,
                                         BoolSearchCondition boolSearchCondition) throws DataSongException {
        // 构建拉取数据条件
        CreatePullDataRequest createPullDataRequest = new CreatePullDataRequest();
        createPullDataRequest.setSearch(boolSearchCondition);
        createPullDataRequest.batchSize(fetchSize);
        // 获取拉取ID
        String pullId = dataService.createPullData(dbName, tableName, createPullDataRequest);
        int deleteCount = 0;
        while (true) {
            String result = dataService.pullData(pullId);
            if (result != null) {
                List<Map<String, Object>> deleteData = DataSongJsonUtils.fromJson(result, List.class);
                // 获取所有要删除数据的_id
                List<String> deleteIds = deleteData.stream()
                        .filter(deleteDatum -> deleteDatum.containsKey("_id"))
                        .map(deleteDatum -> (String) deleteDatum.get("_id"))
                        .toList();
                dataService.batchDeleteData(dbName, tableName, deleteIds);
            } else {
                break;
            }
        }
        return deleteCount;
    }

}
