package com.iscas.datasong.connector.parser;


import cn.hutool.core.util.ReflectUtil;
import com.iscas.datasong.connector.exception.DatasongClientException;
import com.iscas.datasong.connector.util.CollectionUtils;
import com.iscas.datasong.connector.util.StringUtils;
import com.iscas.datasong.lib.common.SortOrder;
import com.iscas.datasong.lib.request.SearchDataRequest;
import com.iscas.datasong.lib.request.search.condition.search.*;
import com.iscas.datasong.lib.request.search.condition.statistic.singleseq.SingleSeqStatisticCondition;
import com.iscas.datasong.lib.request.search.condition.statistic.singleseq.cascade.TermStatisticCondition;
import com.iscas.datasong.lib.request.search.condition.statistic.singleseq.metric.*;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.javatuples.Quartet;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/7/15 17:37
 * @since jdk11
 */
public class SelectSqlParser {

    public static Quartet<String, SearchDataRequest, List<SelectItem>, Boolean> parseSelect(Select select) throws DatasongClientException {
        SearchDataRequest request = new SearchDataRequest();
        // 获取表名
        String tableName = getTableName(select);

        GroupByElement groupBy = getGroupBy(select);

        // 获取查询的列等信息
        List<SelectItem> selectItems = getSelectItems(select);
        boolean all;
        Set<String> selectCols = new HashSet<>();
        if (!CollectionUtils.isEmpty(selectItems)) {
            // 判断查询条件里是否包含*，如果不包含*，将查询的列放在查询条件里
            all = selectItems.stream().anyMatch(item -> item instanceof AllColumns);
            if (!all) {
                for (SelectItem selectItem : selectItems) {
                    if (selectItem instanceof SelectExpressionItem) {
                        SelectExpressionItem selectExpressionItem = (SelectExpressionItem) selectItem;
                        Expression expression = selectExpressionItem.getExpression();
                        all = getSelectCols(selectCols, expression, groupBy != null);
                    }
                }
            }
            if (!all) {
                request.setColumns(selectCols);
            }
        }
        // 构建group by
        List<TermStatisticCondition> termStatisticConditions = createGroupBy(groupBy, selectItems);
        if (CollectionUtils.isNotEmpty(termStatisticConditions)) {
            for (TermStatisticCondition termStatisticCondition : termStatisticConditions) {
                request.addStatistic(termStatisticCondition);
            }
        } else {
            // 构建除了group by之外的count、avg、sum、max、min等函数
            List<SingleSeqStatisticCondition> tscs = createStatistic(selectItems);
            if (CollectionUtils.isNotEmpty(tscs)) {
                tscs.forEach(request::addStatistic);
            }
        }

        // 构建查询条件
        Expression expression = getWhere(select);
        if (!Objects.isNull(expression)) {
            BoolSearchCondition boolSearchCondition = new BoolSearchCondition();
            createSearchCondition(boolSearchCondition, expression, true);
            request.setSearch(boolSearchCondition);
        }

        // 构建limit
        Limit limit = getLimit(select);
        createLimit(request, limit);

        // 构建order by
        List<OrderByElement> orderBy = getOrderBy(select);
        createOrderBy(request, orderBy);

        return Quartet.with(tableName, request, selectItems, CollectionUtils.isNotEmpty(termStatisticConditions));
    }

    private static List<SingleSeqStatisticCondition> createStatistic(List<SelectItem> selectItems) {
        List<SingleSeqStatisticCondition> singleSeqStatisticConditions = new ArrayList<>();
        TermStatisticCondition termStatisticCondition = new TermStatisticCondition();
        termStatisticCondition.setAlias("termStatisticCondition");
        singleSeqStatisticConditions.add(termStatisticCondition);
        // 获取分组内的查询函数
        for (int i = selectItems.size() - 1; i >= 0; i--) {
            SelectItem selectItem = selectItems.get(i);
            if (selectItem instanceof SelectExpressionItem) {
                SelectExpressionItem selectExpressionItem = (SelectExpressionItem) selectItem;
                Alias alias = selectExpressionItem.getAlias();
                Expression expression = selectExpressionItem.getExpression();

                if (expression instanceof Function) {
                    Function function = (Function) expression;
                    List<String> multipartName = function.getMultipartName();
                    ExpressionList parameters = function.getParameters();
                    if (parameters != null) {
                        List<Expression> paramExpressions = parameters.getExpressions();
                        Expression expression1 = paramExpressions.get(0);

                        String funcName = multipartName.get(0);
                        // alias不允许有特殊字符，这里转为base64，并且将base64中的+/替换掉
                        String securityFunctionName = Base64.getEncoder().encodeToString(function.toString().getBytes(StandardCharsets.UTF_8));
                        securityFunctionName = securityFunctionName.replace("+", "iscas123");
                        securityFunctionName = securityFunctionName.replace("/", "Iscas123");
                        securityFunctionName = securityFunctionName.replace("=", "dengyu");

                        if ("COUNT".equalsIgnoreCase(funcName)) {
                            CountStatisticCondition condition = new CountStatisticCondition();
                            condition.setAlias(alias != null ? alias.getName() : securityFunctionName);
                            if (!(expression1 instanceof AllColumns)) {
                                Column column = (Column) expression1;
                                condition.setColumn(column.getColumnName());
                            }
                            termStatisticCondition.addChild(condition);
//                        selectItems.remove(i);
                        } else if ("AVG".equalsIgnoreCase(funcName)) {
                            AvgStatisticCondition condition = new AvgStatisticCondition();
                            condition.setAlias(alias != null ? alias.getName() : securityFunctionName);
                            if (!(expression1 instanceof AllColumns)) {
                                Column column = (Column) expression1;
                                condition.setColumn(column.getColumnName());
                            }
                            termStatisticCondition.addChild(condition);
//                        selectItems.remove(i);
                        } else if ("MAX".equalsIgnoreCase(funcName)) {
                            MaxStatisticCondition condition = new MaxStatisticCondition();
                            condition.setAlias(alias != null ? alias.getName() : securityFunctionName);
                            if (!(expression1 instanceof AllColumns)) {
                                Column column = (Column) expression1;
                                condition.setColumn(column.getColumnName());
                            }
                            termStatisticCondition.addChild(condition);
//                        selectItems.remove(i);
                        } else if ("MIN".equalsIgnoreCase(funcName)) {
                            MinStatisticCondition condition = new MinStatisticCondition();
                            condition.setAlias(alias != null ? alias.getName() : securityFunctionName);
                            if (!(expression1 instanceof AllColumns)) {
                                Column column = (Column) expression1;
                                condition.setColumn(column.getColumnName());
                            }
                            termStatisticCondition.addChild(condition);
//                        selectItems.remove(i);
                        } else if ("SUM".equalsIgnoreCase(funcName)) {
                            SumStatisticCondition condition = new SumStatisticCondition();
                            condition.setAlias(alias != null ? alias.getName() : securityFunctionName);
                            if (!(expression1 instanceof AllColumns)) {
                                Column column = (Column) expression1;
                                condition.setColumn(column.getColumnName());
                            }
                            termStatisticCondition.addChild(condition);
//                        selectItems.remove(i);
                        }
                    }
                }
            }
        }
        return singleSeqStatisticConditions;
    }


    private static void createOrderBy(SearchDataRequest request, List<OrderByElement> orderBys) throws DatasongClientException {
        if (CollectionUtils.isNotEmpty(orderBys)) {
            for (OrderByElement orderBy : orderBys) {
                Expression expression = orderBy.getExpression();
                if (expression instanceof Column) {
                    Column column = (Column) expression;
                    SortOrder sortOrder = SortOrder.ASC;
                    request.sort(column.getColumnName(), orderBy.isAsc() ? sortOrder : SortOrder.DESC);
                } else {
                    throw new DatasongClientException(String.format("不支持的表达式:[%s]", expression.getClass().getName()));
                }
            }
        }
    }

    private static void createLimit(SearchDataRequest request, Limit limit) throws DatasongClientException {
        if (limit != null) {
            Expression rowCount = limit.getRowCount();
            Expression offset = limit.getOffset();
            if (offset instanceof LongValue) {
                request.setStart((int) ((LongValue) offset).getValue());
            } else {
                // 默认offset为0
                request.setStart(0);
            }
            if (rowCount instanceof LongValue) {
                request.setSize((int) ((LongValue) rowCount).getValue());
            } else {
                throw new DatasongClientException(String.format("不支持的limit offset:[%s]", offset.getClass().getName()));
            }
        } else {
            // 默认给最大值
            request.setSize(Integer.MAX_VALUE);
        }
    }

    private static List<TermStatisticCondition> createGroupBy(GroupByElement groupBy, List<SelectItem> selectItems) throws DatasongClientException {
        if (groupBy != null) {
            ExpressionList groupByExpressionList = groupBy.getGroupByExpressionList();
            List<Expression> expressions = groupByExpressionList.getExpressions();
            List<TermStatisticCondition> termStatisticConditions = new ArrayList<>();

            TermStatisticCondition termStatisticCondition = null;
            TermStatisticCondition innerTermStatisticCondition = null;
            for (Expression exp : expressions) {
                if (exp instanceof Column) {
                    Column column = (Column) exp;
                    String columnName = column.getColumnName();
                    if (termStatisticCondition == null) {
                        termStatisticCondition = new TermStatisticCondition();
                        termStatisticCondition.setColumn(columnName);
                        innerTermStatisticCondition = termStatisticCondition;
                    } else {
                        TermStatisticCondition subCondition = new TermStatisticCondition();
                        subCondition.setColumn(columnName);
                        termStatisticCondition.setChildren(subCondition);
                        innerTermStatisticCondition = subCondition;
                    }
                } else {
                    throw new DatasongClientException(String.format("暂不支持的表达式类型:[%s]", exp.getClass().getName()));
                }
            }
            termStatisticConditions.add(termStatisticCondition);

            // 获取分组内的查询函数
            for (SelectItem selectItem : selectItems) {
                if (selectItem instanceof SelectExpressionItem) {
                    SelectExpressionItem selectExpressionItem = (SelectExpressionItem) selectItem;
                    Alias alias = selectExpressionItem.getAlias();
                    Expression expression = selectExpressionItem.getExpression();
                    if (expression instanceof Function) {
                        Function function = (Function) expression;
                        List<String> multipartName = function.getMultipartName();
                        ExpressionList parameters = function.getParameters();
                        List<Expression> paramExpressions = parameters.getExpressions();
                        Expression expression1 = paramExpressions.get(0);

                        String funcName = multipartName.get(0);
                        // alias不允许有特殊字符，这里转为base64，并且将base64中的+/替换掉
                        String securityFunctionName = Base64.getEncoder().encodeToString(function.toString().getBytes(StandardCharsets.UTF_8));
                        securityFunctionName = securityFunctionName.replace("+", "iscas123");
                        securityFunctionName = securityFunctionName.replace("/", "Iscas123");
                        securityFunctionName = securityFunctionName.replace("=", "dengyu");

                        if ("COUNT".equalsIgnoreCase(funcName)) {
                            CountStatisticCondition condition = new CountStatisticCondition();
                            condition.setAlias(alias != null ? alias.getName() : securityFunctionName);
                            if (!(expression1 instanceof AllColumns)) {
                                Column column = (Column) expression1;
                                condition.setColumn(column.getColumnName());
                            }
                            innerTermStatisticCondition.addChild(condition);

                        } else if ("AVG".equalsIgnoreCase(funcName)) {
                            AvgStatisticCondition condition = new AvgStatisticCondition();
                            condition.setAlias(alias != null ? alias.getName() : securityFunctionName);
                            if (!(expression1 instanceof AllColumns)) {
                                Column column = (Column) expression1;
                                condition.setColumn(column.getColumnName());
                            }
                            innerTermStatisticCondition.addChild(condition);
                        } else if ("MAX".equalsIgnoreCase(funcName)) {
                            MaxStatisticCondition condition = new MaxStatisticCondition();
                            condition.setAlias(alias != null ? alias.getName() : securityFunctionName);
                            if (!(expression1 instanceof AllColumns)) {
                                Column column = (Column) expression1;
                                condition.setColumn(column.getColumnName());
                            }
                            innerTermStatisticCondition.addChild(condition);
                        } else if ("MIN".equalsIgnoreCase(funcName)) {
                            MinStatisticCondition condition = new MinStatisticCondition();
                            condition.setAlias(alias != null ? alias.getName() : securityFunctionName);
                            if (!(expression1 instanceof AllColumns)) {
                                Column column = (Column) expression1;
                                condition.setColumn(column.getColumnName());
                            }
                            innerTermStatisticCondition.addChild(condition);
                        } else if ("SUM".equalsIgnoreCase(funcName)) {
                            SumStatisticCondition condition = new SumStatisticCondition();
                            condition.setAlias(alias != null ? alias.getName() : securityFunctionName);
                            if (!(expression1 instanceof AllColumns)) {
                                Column column = (Column) expression1;
                                condition.setColumn(column.getColumnName());
                            }
                            innerTermStatisticCondition.addChild(condition);
                        }
                    }
                }
            }
            return termStatisticConditions;
        }
        return null;
    }

    public static void createSearchCondition(BoolSearchCondition searchCondition, Expression expression, boolean and) throws DatasongClientException {
        if (expression instanceof OrExpression) {
            // or连接的条件
            or(searchCondition, (OrExpression) expression);
        } else if (expression instanceof Parenthesis) {
            // 括号
            parenthesis(searchCondition, (Parenthesis) expression, and);
        } else if (expression instanceof AndExpression) {
            // and 连接的条件
            and(searchCondition, (AndExpression) expression);
        } else if (expression instanceof GreaterThan || expression instanceof EqualsTo ||
                expression instanceof GreaterThanEquals || expression instanceof MinorThan ||
                expression instanceof MinorThanEquals || expression instanceof LikeExpression ||
                expression instanceof NotEqualsTo || expression instanceof InExpression ||
                expression instanceof Between) {
            // x > 1                  x = 1
            // x >= 1                 x < 1
            // x <= 1                 x like '%xxx%' x not like '%eee%'
            // x != 1                 x in(1, 2)  x not in (1, 2)
            // x between 1 and 2
            createCondition(searchCondition, expression, and);
        } else {
            throw new DatasongClientException(String.format("暂不支持的表达式类型:[%s]", expression.getClass().getName()));
        }
    }

    private static void createCondition(BoolSearchCondition searchCondition, Expression expression, boolean and) throws DatasongClientException {
        Expression leftExpression = (Expression) ReflectUtil.getFieldValue(expression, "leftExpression");
        Expression rightExpression = (Expression) ReflectUtil.getFieldValue(expression, "rightExpression");
        if (leftExpression instanceof Column) {
            Column column = (Column) leftExpression;
            String columnName = column.getColumnName();
            ISearchCondition baseCondition = createISearchCondition(columnName, expression, rightExpression);
            if (and) {
                searchCondition.getMust().add(baseCondition);
            } else {
                searchCondition.getShould().add(baseCondition);
            }
        } else {
            throw new DatasongClientException(String.format("暂不支持的表达式类型:[%s]", leftExpression.getClass().getName()));
        }
    }

    private static ISearchCondition createISearchCondition(String columnName, Expression expression, Expression rightExpression) throws DatasongClientException {
        if (expression instanceof GreaterThan) {
            return createRangeCondition(columnName, getData(rightExpression), null, false, false);
        } else if (expression instanceof EqualsTo) {
            return createEqualsCondition(columnName, rightExpression);
        } else if (expression instanceof GreaterThanEquals) {
            return createRangeCondition(columnName, getData(rightExpression), null, true, false);
        } else if (expression instanceof MinorThan) {
            return createRangeCondition(columnName, null, getData(rightExpression), false, false);
        } else if (expression instanceof MinorThanEquals) {
            return createRangeCondition(columnName, null, getData(rightExpression), false, true);
        } else if (expression instanceof LikeExpression) {
            return createWildcardCondition(columnName, rightExpression, ((LikeExpression) expression).isNot());
        } else if (expression instanceof NotEqualsTo) {
            return createNotEqualsCondition(columnName, rightExpression);
        } else if (expression instanceof InExpression) {
            return createInCondition(columnName, ((InExpression) expression).getRightItemsList(), ((InExpression) expression).isNot());
        } else if (expression instanceof Between) {
            Between between = (Between) expression;
            Expression betweenExpressionStart = between.getBetweenExpressionStart();
            Expression betweenExpressionEnd = between.getBetweenExpressionEnd();
            return createRangeCondition(columnName, getData(betweenExpressionStart), getData(betweenExpressionEnd), true, true);
        } else {
            throw new DatasongClientException(String.format("暂不支持的表达式类型:[%s]", expression.getClass().getName()));
        }
    }

    private static ISearchCondition createRangeCondition(String columnName, Object from, Object to,
                                                         boolean includeFrom, boolean includeTo) throws DatasongClientException {
        RangeSearchCondition rangeSearchCondition = new RangeSearchCondition();
        rangeSearchCondition.setColumn(columnName);
        rangeSearchCondition.setFrom(from);
        rangeSearchCondition.setTo(to);
        rangeSearchCondition.setIncludeFrom(includeFrom);
        rangeSearchCondition.setIncludeTo(includeTo);
        return rangeSearchCondition;
    }

    private static ISearchCondition createInCondition(String columnName, ItemsList right, boolean not) throws DatasongClientException {
        TermSearchCondition termSearchCondition = new TermSearchCondition();
        termSearchCondition.setColumn(columnName);
        if (right instanceof ExpressionList) {
            ExpressionList expressionList = (ExpressionList) right;
            List<Expression> expressions = expressionList.getExpressions();
            Object[] values = new Object[expressions.size()];
            for (int i = 0; i < expressions.size(); i++) {
                values[i] = getData(expressions.get(i));
            }
            termSearchCondition.setValue(values);
        } else {
            throw new DatasongClientException(String.format("暂不支持的表达式类型:[%s]", right.getClass().getName()));
        }
        if (!not) {
            return termSearchCondition;
        }
        BoolSearchCondition boolSearchCondition = new BoolSearchCondition();
        boolSearchCondition.mustNot(termSearchCondition);
        return boolSearchCondition;
    }

    private static ISearchCondition createWildcardCondition(String columnName, Expression rightExpression, boolean not) throws DatasongClientException {
        WildcardSearchCondition wildcardSearchCondition = new WildcardSearchCondition();
        wildcardSearchCondition.setColumn(columnName);
        wildcardSearchCondition.setValue(String.valueOf(getData(rightExpression)));
        if (!not) {
            return wildcardSearchCondition;
        }
        BoolSearchCondition boolSearchCondition = new BoolSearchCondition();
        boolSearchCondition.mustNot(wildcardSearchCondition);
        return boolSearchCondition;
    }

    private static ISearchCondition createEqualsCondition(String columnName, Expression rightExpression) throws DatasongClientException {
        TermSearchCondition termSearchCondition = new TermSearchCondition();
        termSearchCondition.setColumn(columnName);
        termSearchCondition.setValue(getData(rightExpression));
        return termSearchCondition;
    }

    private static ISearchCondition createNotEqualsCondition(String columnName, Expression rightExpression) throws DatasongClientException {
        BoolSearchCondition boolSearchCondition = new BoolSearchCondition();
        TermSearchCondition termSearchCondition = new TermSearchCondition();
        termSearchCondition.setColumn(columnName);
        termSearchCondition.setValue(getData(rightExpression));
        boolSearchCondition.mustNot(termSearchCondition);
        return boolSearchCondition;
    }

    public static Object getData(Expression expression) throws DatasongClientException {
        if (expression instanceof LongValue) {
            LongValue longValue = (LongValue) expression;
            return longValue.getValue();
        } else if (expression instanceof StringValue) {
            StringValue stringValue = (StringValue) expression;
            return stringValue.getValue();
        } else if (expression instanceof NullValue) {
            return null;
        } else if (expression instanceof DoubleValue) {
            DoubleValue doubleValue = (DoubleValue) expression;
            return doubleValue.getValue();
        } else {
            throw new DatasongClientException(String.format("暂不支持的表达式类型:[%s]", expression.getClass().getName()));
        }
    }

    private static void parenthesis(BoolSearchCondition searchCondition, Parenthesis expression, boolean and) throws DatasongClientException {
        Parenthesis parenthesis = expression;
        createSearchCondition(searchCondition, parenthesis.getExpression(), and);
    }

    private static void and(BoolSearchCondition searchCondition, AndExpression expression) throws DatasongClientException {
        AndExpression andExpression = expression;
        Expression leftExpression = andExpression.getLeftExpression();
        Expression rightExpression = andExpression.getRightExpression();
        if (!Objects.isNull(leftExpression)) {
            BoolSearchCondition leftCondition = new BoolSearchCondition();
            searchCondition.getMust().add(leftCondition);
            createSearchCondition(leftCondition, leftExpression, true);
        }
        if (!Objects.isNull(rightExpression)) {
            BoolSearchCondition rightCondition = new BoolSearchCondition();
            searchCondition.getMust().add(rightCondition);
            createSearchCondition(rightCondition, rightExpression, true);
        }
    }

    private static void or(BoolSearchCondition searchCondition, OrExpression expression) throws DatasongClientException {
        OrExpression orExpression = expression;
        Expression leftExpression = orExpression.getLeftExpression();
        Expression rightExpression = orExpression.getRightExpression();
        if (!Objects.isNull(leftExpression)) {
            BoolSearchCondition leftCondition = new BoolSearchCondition();
            searchCondition.getShould().add(leftCondition);
            createSearchCondition(leftCondition, leftExpression, false);
        }
        if (!Objects.isNull(rightExpression)) {
            BoolSearchCondition rightCondition = new BoolSearchCondition();
            searchCondition.getShould().add(rightCondition);
            createSearchCondition(rightCondition, rightExpression, false);
        }
    }

    private static boolean getSelectCols(Set<String> selectCols, Expression expression, boolean withGroupBy) {
        // 根据不同的expression类型获取查询的列
        if (expression instanceof AllColumns) {
            return true;
        } else if (expression instanceof Column) {
            Column column = (Column) expression;
            String columnName = column.getColumnName();
            if (!(columnName.startsWith("\"") || columnName.startsWith("'") ||
                    columnName.endsWith("\"") || columnName.endsWith("'"))) {
                selectCols.add(column.getColumnName());
            }
        } else if (expression instanceof Function) {
            Function function = (Function) expression;
            ExpressionList parameters = function.getParameters();
            List<String> multipartName = function.getMultipartName();
            if (!StringUtils.equalsAny(multipartName.get(0), "count", "sum", "avg", "max", "min")) {
                if (parameters != null) {
                    for (Expression parametersExpression : parameters.getExpressions()) {
                        boolean selectCols1 = getSelectCols(selectCols, parametersExpression, withGroupBy);
                        if (selectCols1) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static String getTableName(Statement statement) throws DatasongClientException {
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        List<String> tableNames = tablesNamesFinder.getTableList(statement);
        if (CollectionUtils.isEmpty(tableNames)) {
            throw new DatasongClientException("无法从SQL语句中获取表名");
        }
        if (tableNames.size() > 1) {
            throw new DatasongClientException("暂不支持多表操作");
        }
        return tableNames.get(0);
    }

    public static List<SelectItem> getSelectItems(Select select) {
        PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
        return plainSelect.getSelectItems();
    }

    public static Expression getWhere(Select select) {
        PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
        return plainSelect.getWhere();
    }

    public static List<OrderByElement> getOrderBy(Select select) {
        PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
        return plainSelect.getOrderByElements();
    }

    public static Limit getLimit(Select select) {
        PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
        return plainSelect.getLimit();
    }

    public static GroupByElement getGroupBy(Select select) {
        PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
        return plainSelect.getGroupBy();
    }

    public static void main(String[] args) throws JSQLParserException, DatasongClientException {
        // 获取表名
        String sql1 = "SELECT * FROM user t WHERE t.x in (SELECT x FROM user a)";
        System.out.println(getTableName(CCJSqlParserUtil.parse(sql1)));

        String sql2 = "SELECT t.x AS px, sum(t.y) AS py, t.z AS pz, m, pow(x, y) FROM user t WHERE t.x in (SELECT x FROM user a)";
        List<SelectItem> selectItems = getSelectItems((Select) CCJSqlParserUtil.parse(sql2));
        System.out.println(selectItems);

        String sql3 = "SELECT *, t.x AS px, sum(t.y) AS py, t.z AS pz FROM user t WHERE t.x in (SELECT x FROM user a)";
        List<SelectItem> selectItems2 = getSelectItems((Select) CCJSqlParserUtil.parse(sql3));
        System.out.println(selectItems2);

        Expression where = getWhere((Select) CCJSqlParserUtil.parse(sql3));
        System.out.println(where);

        String sql4 = "SELECT *, t.x AS px, sum(t.y) AS py, t.z AS pz FROM user t WHERE (" +
                " isNull(t.b) AND t.a = null AND t.b < 10 AND t.x = 4 AND t.y > 10 AND t.z in (1, 2) AND " +
                "t.p != 10 OR (t.m = 10 AND t.n like '%ppp%') AND t.e like '%xxx%' AND t.x NOT LIKE '%we%' AND t.p in (1, 2, 3)" +
                " AND t.j BETWEEN 1.8 AND 6.8)";
        Expression where2 = getWhere((Select) CCJSqlParserUtil.parse(sql4));
        System.out.println(where2);

        String sql5 = "SELECT COUNT(*) FROM test WHERE a > 1 GROUP BY name";
        GroupByElement groupBy = getGroupBy((Select) CCJSqlParserUtil.parse(sql5));
        System.out.println(groupBy);

        Statement statement = CCJSqlParserUtil.parse("SELECT * FROM TEST WHERE a like '%www' " +
                "AND x > 10 AND y = 'xxxx' AND p BETWEEN 5.6 AND 9.0 OR (z = 10 AND p <= 100)");
        Quartet<String, SearchDataRequest, List<SelectItem>, Boolean> quartet1 = parseSelect((Select) statement);
        System.out.println(quartet1.getValue1());

        Statement statement2 = CCJSqlParserUtil.parse("SELECT COUNT(*), SUM(age) FROM test WHERE a > 1 GROUP BY name, age");
        Quartet<String, SearchDataRequest, List<SelectItem>, Boolean> quartet2 = parseSelect((Select) statement2);
        System.out.println(quartet2.getValue1());

        Statement statement3 = CCJSqlParserUtil.parse("SELECT * FROM test WHERE age > 1 limit 0, 10");
        Quartet<String, SearchDataRequest, List<SelectItem>, Boolean> quartet3 = parseSelect((Select) statement3);
        System.out.println(quartet3.getValue1());

        Statement statement4 = CCJSqlParserUtil.parse("SELECT * FROM test WHERE age > 1 order by a desc, b limit 0, 10");
        Quartet<String, SearchDataRequest, List<SelectItem>, Boolean> quartet4 = parseSelect((Select) statement4);
        System.out.println(quartet4.getValue1());
    }

}
