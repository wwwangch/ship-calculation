package com.iscas.common.tools.sql;

import com.iscas.common.tools.constant.StrConstantEnum;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitorAdapter;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.ComparisonOperator;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.util.TablesNamesFinder;
import net.sf.jsqlparser.util.deparser.ExpressionDeParser;
import org.apache.commons.collections4.MapUtils;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/9/6 9:44
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public class SqlParserUtils {

    /**
     * 获取查询的SQL字段
     */
    public static List<String> selectItems(String sql)
            throws JSQLParserException {
        CCJSqlParserManager parserManager = new CCJSqlParserManager();
        Select select = (Select) parserManager.parse(new StringReader(sql));
        PlainSelect plain = (PlainSelect) select.getSelectBody();
        List<SelectItem> selectitems = plain.getSelectItems();
        List<String> strItems = new ArrayList<>();
        if (selectitems != null) {
            for (SelectItem selectitem : selectitems) {
                strItems.add(selectitem.toString());
            }
        }
        return strItems;
    }

    /**
     * 获取表名
     */
    public static List<String> selectTable(String sql)
            throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse(sql);
        Select selectStatement = (Select) statement;
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        return tablesNamesFinder.getTableList(selectStatement);
    }

    /**
     * 获取JOIN
     */
    public static List<String> selectJoin(String sql)
            throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse(sql);
        Select selectStatement = (Select) statement;
        PlainSelect plain = (PlainSelect) selectStatement.getSelectBody();
        List<Join> joinList = plain.getJoins();
        List<String> tablewithjoin = new ArrayList<>();
        if (joinList != null) {
            for (Join join : joinList) {
                //是否开放left jion中的left
                join.setLeft(true);
                tablewithjoin.add(join.toString());
            }
        }
        return tablewithjoin;
    }

    /**
     * 获取where
     */
    public static String selectWhere(String sql)
            throws JSQLParserException {
        CCJSqlParserManager parserManager = new CCJSqlParserManager();
        Select select = (Select) parserManager.parse(new StringReader(sql));
        PlainSelect plain = (PlainSelect) select.getSelectBody();
        Expression whereExpression = plain.getWhere();
        return whereExpression.toString();
    }

    /**
     * 解析where
     */
    public static List<Map<String, Object>> parseWhere(String sql) {
        try {
            Select select = (Select) CCJSqlParserUtil.parse(sql);
            SelectBody selectBody = select.getSelectBody();
            PlainSelect plainSelect = (PlainSelect) selectBody;
            Expression expr = CCJSqlParserUtil.parseCondExpression(plainSelect.getWhere().toString());
            List<Map<String, Object>> arrList = new ArrayList<>();
            expr.accept(new ExpressionDeParser() {
                int depth = 0;

                @Override
                public void visit(Parenthesis parenthesis) {
                    depth++;
                    parenthesis.getExpression().accept(this);
                    depth--;
                }

                @Override
                public void visit(OrExpression orExpression) {
                    visitBinaryExpr(orExpression, "OR");
                }

                @Override
                public void visit(AndExpression andExpression) {
                    visitBinaryExpr(andExpression, "AND");
                }

                private void visitBinaryExpr(BinaryExpression expr, String operator) {
                    Map<String, Object> map = new HashMap<>(32);
                    if (!(expr.getLeftExpression() instanceof OrExpression)
                            && !(expr.getLeftExpression() instanceof AndExpression)
                            && !(expr.getLeftExpression() instanceof Parenthesis)) {
                        //noinspection ResultOfMethodCallIgnored
                        getBuffer();
                    }
                    expr.getLeftExpression().accept(this);
                    map.put("leftExpression", expr.getLeftExpression());
                    map.put("operator", operator);
                    if (!(expr.getRightExpression() instanceof OrExpression)
                            && !(expr.getRightExpression() instanceof AndExpression)
                            && !(expr.getRightExpression() instanceof Parenthesis)) {
                        //noinspection ResultOfMethodCallIgnored
                        getBuffer();
                    }
                    expr.getRightExpression().accept(this);
                    map.put("rightExpression", expr.getRightExpression());
                    arrList.add(map);
                }
            });
            return arrList;
        } catch (JSQLParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析where单个条件
     */
    public static Map<Object, Object> fullResolutionWhere(String where) {
        Map<Object, Object> map = new HashMap<>(16);
        try {
            Expression expr = CCJSqlParserUtil.parseCondExpression(where);
            expr.accept(new ExpressionVisitorAdapter() {
                @Override
                protected void visitBinaryExpression(BinaryExpression expr) {
                    if (expr instanceof ComparisonOperator) {
                        map.put("leftExpression", expr.getLeftExpression());
                        map.put("operate", expr.getStringExpression());
                        map.put("rightExpression", expr.getRightExpression());
                    }
                    super.visitBinaryExpression(expr);
                }
            });
            //暂时无法解析IS NOT NULL 和 IS NULL
            boolean b = MapUtils.isEmpty(map) && (where.toUpperCase().contains("IS NOT NULL") || where.toUpperCase().contains("IS NULL"));
            if (b) {
                map.put("leftExpression", where.substring(0, where.lastIndexOf("IS")));
                map.put("operate", null);
                map.put("rightExpression", where.substring(where.lastIndexOf("IS")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 获取group by
     */
    @SuppressWarnings("deprecation")
    public static List<String> selectGroupBy(String sql)
            throws JSQLParserException {
        CCJSqlParserManager parserManager = new CCJSqlParserManager();
        Select select = (Select) parserManager.parse(new StringReader(sql));
        PlainSelect plain = (PlainSelect) select.getSelectBody();
        List<Expression> groupByColumnReferences = plain.getGroupBy().getGroupByExpressions();
        List<String> strGroupby = new ArrayList<>();
        if (groupByColumnReferences != null) {
            for (Expression groupByColumnReference : groupByColumnReferences) {
                strGroupby.add(groupByColumnReference.toString());
            }
        }
        return strGroupby;
    }

    /**
     * 获取order by
     */
    public static List<String> selectOrderBy(String sql)
            throws JSQLParserException {
        CCJSqlParserManager parserManager = new CCJSqlParserManager();
        Select select = (Select) parserManager.parse(new StringReader(sql));
        PlainSelect plain = (PlainSelect) select.getSelectBody();
        List<OrderByElement> orderByElements = plain.getOrderByElements();
        List<String> strOrderby = new ArrayList<>();
        if (orderByElements != null) {
            for (OrderByElement orderByElement : orderByElements) {
                strOrderby.add(orderByElement.toString());
            }
        }
        return strOrderby;
    }

    /**
     * 获取子查询
     */
    @SuppressWarnings("rawtypes")
    public static Map selectSubSelect(SelectBody selectBody) {
        Map<String, String> map = new HashMap<>(16);
        if (selectBody instanceof PlainSelect plainSelect) {
            List<SelectItem> selectItems = plainSelect.getSelectItems();
            for (SelectItem selectItem : selectItems) {
                if (selectItem.toString().contains("(") && selectItem.toString().contains(")")) {
                    map.put("selectItemsSubselect", selectItem.toString());
                }
            }
            Expression where = ((PlainSelect) selectBody).getWhere();
            if (where != null) {
                String whereStr = where.toString();
                if (whereStr.contains(StrConstantEnum.LEFT_BRACKET.getValue()) && whereStr.contains(StrConstantEnum.RIGHT_BRACKET.getValue())) {
                    int firstIndex = whereStr.indexOf(StrConstantEnum.LEFT_BRACKET.getValue());
                    int lastIndex = whereStr.lastIndexOf(StrConstantEnum.RIGHT_BRACKET.getValue());
                    CharSequence charSequence = whereStr.subSequence(firstIndex, lastIndex + 1);
                    map.put("whereSubselect", charSequence.toString());
                }
            }
            FromItem fromItem = ((PlainSelect) selectBody).getFromItem();
            if (fromItem instanceof SubSelect) {
                map.put("fromItemSubselect", fromItem.toString());
            }
        } else if (selectBody instanceof WithItem selectBodyWithItem) {
            //???
            SqlParserUtils.selectSubSelect(selectBodyWithItem.getSubSelect().getSelectBody());
        }
        return map;
    }

    /**
     * 判断是否为多级子查询
     */
    public static boolean isMultiSubSelect(SelectBody selectBody) {
        if (selectBody instanceof PlainSelect plainSelect) {
            FromItem fromItem = plainSelect.getFromItem();
            if (fromItem instanceof SubSelect subSelect) {
                SelectBody subBody = subSelect.getSelectBody();
                if (subBody instanceof PlainSelect subPlainSelect) {
                    FromItem subFromItem = subPlainSelect.getFromItem();
                    return subFromItem instanceof SubSelect;
                }
            }
        }
        return false;
    }

}
