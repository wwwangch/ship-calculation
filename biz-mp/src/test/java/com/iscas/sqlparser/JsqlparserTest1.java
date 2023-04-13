package com.iscas.sqlparser;

import com.alibaba.druid.filter.config.ConfigTools;
import com.google.common.collect.Sets;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.statement.update.Update;
import net.sf.jsqlparser.util.SelectUtils;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/1/7 16:01
 * @since jdk1.8
 */
public class JsqlparserTest1 {

    /**
     * 判断SQL语句是否是查询
     * */
    @Test
    public void testCheckSql() throws JSQLParserException {
        String sql = "SELECT username, age, sex FROM user";
        Statement select = CCJSqlParserUtil.parse(sql);
        Assertions.assertTrue(select instanceof Select);

        String sql2 = "delete from  user where id = 1";
        Statement delete = CCJSqlParserUtil.parse(sql2);
        Assertions.assertTrue(delete instanceof Delete);

    }

    @Test
    public void testSelect1() throws JSQLParserException {
        String sql = "SELECT username, age, sex FROM user";
        //使用工具类把SQL转换为Select对象
        Select select = (Select) CCJSqlParserUtil.parse(sql);
        SelectBody selectBody = select.getSelectBody();
        System.out.println(selectBody);
    }

    /**
     * 测试查询SelectBody
     */
    @Test
    public void testSelect2() throws JSQLParserException {
        String sql = "SELECT * FROM user";
        //使用工具类把SQL转换为Select对象
        Select select = (Select) CCJSqlParserUtil.parse(sql);
        SelectBody selectBody = select.getSelectBody();
        System.out.println(selectBody);
    }

    /**
     * 构建简单查询语句
     */
    @Test
    public void testQuery() throws JSQLParserException {
        Select select1 = SelectUtils.buildSelectFromTable(new Table("test"));
        System.out.println(select1.toString());

        Select select2 = SelectUtils.buildSelectFromTableAndExpressions(new Table("test"), "name", "age", "flag");
        System.out.println(select2.toString());

        Select select3 = SelectUtils.buildSelectFromTableAndExpressions(new Table("test"), new Column("name"),
                new Column("age"));
        System.out.println(select3.toString());
    }

    /**
     * 构建插入语句
     */
    @Test
    public void testInsert() throws JSQLParserException {
        Insert insert = new Insert();

        //插入表
        Table table = new Table("test");
        insert.setTable(table);

        //设置插入列
        List<Column> columns = new ArrayList<>() {{
            add(new Column("name"));
            add(new Column("age"));
        }};
        insert.setColumns(columns);

        //设置插入的值
        MultiExpressionList multiExpressionList = new MultiExpressionList();
        multiExpressionList.addExpressionList(new StringValue("zhangsan"), new HexValue("22"));
        insert.setItemsList(multiExpressionList);

        System.out.println(insert);
    }

    /**
     * 构建更新语句
     */
    @Test
    public void testUpdate() throws JSQLParserException {
        Update update = new Update();

        //插入表
        Table table = new Table("test");
        update.setTable(table);

        //设置更新列
        List<Column> columns = Arrays.asList(new Column("name"), new Column("age"));
        update.withColumns(columns);

        //设置更新的值
        update.withExpressions(Arrays.asList(new StringValue("zhangsan"), new StringValue("22")));

        //添加where条件
        EqualsTo equals = new EqualsTo();
        //设置表达式左边的值
        equals.setLeftExpression(new Column(table, "id"));
        equals.setRightExpression(new HexValue("1"));
        update.setWhere(equals);

        System.out.println(update);
    }

    /**
     * 构建删除语句
     * */
    @Test
    public void testDelete() {
        Table table = new Table();
        table.setName("test");

        Delete delete = new Delete();
        delete.setTable(table);

        //设置表达式左边的值
        EqualsTo equals = new EqualsTo();
        equals.setLeftExpression(new Column(table, "id"));
        equals.setRightExpression(new HexValue("1"));
        delete.setWhere(equals);
        System.out.println(delete);
    }

    /**
     * 单表SQL查询
     *
     */
    @Test
    public void testSelectOneTable() throws JSQLParserException {
        // 单表全量
        Table table = new Table("test");
        Select select = SelectUtils.buildSelectFromTable(table);
        System.err.println(select); // SELECT * FROM test

        // 指定列查询
        Select buildSelectFromTableAndExpressions = SelectUtils.buildSelectFromTableAndExpressions(new Table("test"), new Column("col1"), new Column("col2"));
        System.err.println(buildSelectFromTableAndExpressions); // SELECT col1, col2 FROM test

        // WHERE =
        EqualsTo equalsTo = new EqualsTo(); // 等于表达式
        equalsTo.setLeftExpression(new Column(table, "user_id")); // 设置表达式左边值
        equalsTo.setRightExpression(new StringValue("123456"));// 设置表达式右边值
        PlainSelect plainSelect = (PlainSelect) select.getSelectBody(); // 转换为更细化的Select对象
        plainSelect.setWhere(equalsTo);
        System.err.println(plainSelect);//  SELECT * FROM test WHERE test.user_id = '123456'

        // WHERE  != <>
        NotEqualsTo notEqualsTo = new NotEqualsTo();
        notEqualsTo.setLeftExpression(new Column(table, "user_id")); // 设置表达式左边值
        notEqualsTo.setRightExpression(new StringValue("123456"));// 设置表达式右边值
        PlainSelect plainSelectNot = (PlainSelect) select.getSelectBody();
        plainSelectNot.setWhere(notEqualsTo);
        System.err.println(plainSelectNot);//  SELECT * FROM test WHERE test.user_id <> '123456'

        // 其他运算符, 参考上面代码添加表达式即可
        GreaterThan gt = new GreaterThan(); // ">"
        GreaterThanEquals geq = new GreaterThanEquals(); // ">="
        MinorThan mt = new MinorThan(); // "<"
        MinorThanEquals leq = new MinorThanEquals();// "<="
        IsNullExpression isNull = new IsNullExpression(); // "is null"
        isNull.setNot(true);// "is not null"
        LikeExpression nlike = new LikeExpression();
        nlike.setNot(true); // "not like"
        Between bt = new Between();
        bt.setNot(true);// "not between"

        // WHERE LIKE
        LikeExpression likeExpression = new LikeExpression(); // 创建Like表达式对象
        likeExpression.setLeftExpression(new Column("username")); // 表达式左边
        likeExpression.setRightExpression(new StringValue("张%")); // 右边表达式
        PlainSelect plainSelectLike = (PlainSelect) select.getSelectBody();
        plainSelectLike.setWhere(likeExpression);
        System.err.println(plainSelectLike); // SELECT * FROM test WHERE username LIKE '张%'

        // WHERE IN
        Set<String> deptIds = Sets.newLinkedHashSet(); // 创建IN范围的元素集合
        deptIds.add("0001");
        deptIds.add("0002");
        ItemsList itemsList = new ExpressionList(deptIds.stream().map(StringValue::new).collect(Collectors.toList())); // 把集合转变为JSQLParser需要的元素列表
        InExpression inExpression = new InExpression(new Column("dept_id "), itemsList); // 创建IN表达式对象，传入列名及IN范围列表
        PlainSelect plainSelectIn = (PlainSelect) select.getSelectBody();
        plainSelectIn.setWhere(inExpression);
        System.err.println(plainSelectIn); // SELECT * FROM test WHERE dept_id  IN ('0001', '0002')

        // WHERE BETWEEN AND
        Between between = new Between();
        between.setBetweenExpressionStart(new LongValue(18)); // 设置起点值
        between.setBetweenExpressionEnd(new LongValue(30)); // 设置终点值
        between.setLeftExpression(new Column("age")); // 设置左边的表达式，一般为列
        PlainSelect plainSelectBetween = (PlainSelect) select.getSelectBody();
        plainSelectBetween.setWhere(between);
        System.err.println(plainSelectBetween); // SELECT * FROM test WHERE age BETWEEN 18 AND 30

        //  WHERE AND 多个条件结合,都需要成立
        AndExpression andExpression = new AndExpression(); // AND 表达式
        andExpression.setLeftExpression(equalsTo); // AND 左边表达式
        andExpression.setRightExpression(between);  // AND 右边表达式
        PlainSelect plainSelectAnd = (PlainSelect) select.getSelectBody();
        plainSelectAnd.setWhere(andExpression);
        System.err.println(plainSelectAnd); //  SELECT * FROM test WHERE test.user_id = '123456' AND age BETWEEN 18 AND 30

        //  WHERE OR 多个条件满足一个条件成立返回
        OrExpression orExpression = new OrExpression();// OR 表达式
        orExpression.setLeftExpression(equalsTo); // OR 左边表达式
        orExpression.setRightExpression(between);  // OR 右边表达式
        PlainSelect plainSelectOr = (PlainSelect) select.getSelectBody();
        plainSelectOr.setWhere(orExpression);
        System.err.println(plainSelectOr); // SELECT * FROM test WHERE test.user_id = '123456' OR age BETWEEN 18 AND 30

        // ORDER BY 排序
        OrderByElement orderByElement = new OrderByElement(); // 创建排序对象
        orderByElement.isAsc(); //  设置升序排列 从小到大
        orderByElement.setExpression(new Column("col01")); // 设置排序字段
        PlainSelect plainSelectOrderBy = (PlainSelect) select.getSelectBody();
        plainSelectOrderBy.addOrderByElements(orderByElement);
        System.err.println(plainSelectOrderBy); // SELECT * FROM test WHERE test.user_id = '123456' OR age BETWEEN 18 AND 30 ORDER BY col01
    }

    /**
     * 多表SQL查询
     * JOIN / INNER JOIN: 如果表中有至少一个匹配，则返回行
     * LEFT JOIN: 即使右表中没有匹配，也从左表返回所有的行
     * RIGHT JOIN: 即使左表中没有匹配，也从右表返回所有的行
     * FULL JOIN: 只要其中一个表中存在匹配，就返回行
     */
    @Test
    public void testSelectManyTable() {
        Table t1 = new Table("tab1").withAlias(new Alias("t1").withUseAs(true)); // 表1
        Table t2 = new Table("tab2").withAlias(new Alias("t2", false)); // 表2
        PlainSelect plainSelect = new PlainSelect().addSelectItems(new AllColumns()).withFromItem(t1); // SELECT * FROM tab1 AS t1

        // JOIN ON 如果表中有至少一个匹配，则返回行
        Join join = new Join(); // 创建Join对象
        join.withRightItem(t2); // 添加Join的表 JOIN t2 =>JOIN tab2 t2
        EqualsTo equalsTo = new EqualsTo(); // 添加 = 条件表达式  t1.user_id  = t2.user_id
        equalsTo.setLeftExpression(new Column(t1, "user_id "));
        equalsTo.setRightExpression(new Column(t2, "user_id "));
        join.withOnExpression(equalsTo);// 添加ON
        plainSelect.addJoins(join);
        System.err.println(plainSelect); // SELECT * FROM tab1 AS t1 JOIN tab2 t2 ON t1.user_id  = t2.user_id

        // 设置join参数可实现其他类型join
        // join.setLeft(true); LEFT JOIN
        // join.setRight(true);  RIGHT JOIN
        // join.setFull(true); FULL JOIN
        // join.setInner(true);
    }

    /**
     * SQL 函数
     * SELECT function(列) FROM 表
     */
    @Test
    public void testFun() throws JSQLParserException {
        Table t1 = new Table("tab1").withAlias(new Alias("t1").withUseAs(true)); // 表1
        PlainSelect plainSelect = new PlainSelect();
        plainSelect.setFromItem(t1); // 设置FROM t1= >  SELECT  FROM tab1 AS t1
        List<SelectItem> selectItemList = new ArrayList<>(); // 查询元素集合
        SelectExpressionItem selectExpressionItem001 = new SelectExpressionItem(); // 元素1表达式
        selectExpressionItem001.setExpression(new Column(t1,"col001"));
        SelectExpressionItem selectExpressionItem002 = new SelectExpressionItem(); // 元素2表达式
        selectExpressionItem002.setExpression(new Column(t1,"col002"));
        selectItemList.add(0, selectExpressionItem001); // 添加入队
        selectItemList.add(1, selectExpressionItem002); // 添加入队

        // COUNT
        SelectExpressionItem selectExpressionItemCount = new SelectExpressionItem(); // 创建函数元素表达式
        selectExpressionItemCount.setAlias(new Alias("count")); // 设置别名
        Function function = new Function(); // 创建函数对象  Function extends ASTNodeAccessImpl implements Expression
        function.setName("COUNT"); // 设置函数名
        ExpressionList expressionListCount = new ExpressionList(); // 创建参数表达式
        expressionListCount.setExpressions(Collections.singletonList(new Column(t1, "id")));
        function.setParameters(expressionListCount); // 设置参数
        selectExpressionItemCount.setExpression(function);
        selectItemList.add(2,selectExpressionItemCount);

        plainSelect.setSelectItems(selectItemList); // 添加查询元素集合入select对象
        System.err.println(plainSelect); // SELECT t1.col001, t1.col002, COUNT(t1.id) AS count FROM tab1 AS t1
    }

    /**
     * SQL 解析
     *
     * @throws JSQLParserException
     */
    @Test
    public void testSelectParser() throws JSQLParserException {
        String SQL002 = "SELECT t1.a , t1.b  FROM tab1 AS t1 JOIN tab2 t2 ON t1.user_id  = t2.user_id";   // 多表SQL

        // 1.解析表名
        CCJSqlParserManager parserManager = new CCJSqlParserManager();
        Statement statement = parserManager.parse(new StringReader(SQL002)); // 解析SQL为Statement对象
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder(); // 创建表名发现者对象
        List<String> tableNameList = tablesNamesFinder.getTableList(statement); // 获取到表名列表
        if (!CollectionUtils.isEmpty(tableNameList)) {
            tableNameList.forEach(System.err::println); // 循环打印解析到的表名 tab1 tab2
        }
        // 2.解析查询元素=》 列，函数等
        Select select = (Select) CCJSqlParserUtil.parse(SQL002);
        PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
        List<SelectItem> selectItems = plainSelect.getSelectItems();
        selectItems.forEach(System.err::println); // t1.a , t1.b

        // 3.解析WHERE条件
        String SQL_WHERE = "SELECT *  FROM tableName WHERE ID = 8";
        PlainSelect plainSelectWhere = (PlainSelect) ((Select) CCJSqlParserUtil.parse(SQL_WHERE)).getSelectBody();
        EqualsTo equalsTo = (EqualsTo) plainSelectWhere.getWhere();
        Expression leftExpression = equalsTo.getLeftExpression();
        Expression rightExpression = equalsTo.getRightExpression();
        System.err.println(leftExpression); // ID
        System.err.println(rightExpression); // 8

        // 4.解析Join
        List<Join> joins = plainSelect.getJoins();
        joins.forEach(e -> {
            Expression onExpression = e.getOnExpression();
            System.err.println(onExpression); // 获取ON 表达式 t1.user_id = t2.user_id
        });

        // 5.解析IN
        String SQL_IN = "SELECT *  FROM tableName WHERE ID IN (8,9,10)";
        PlainSelect plainSelectIn = (PlainSelect) ((Select) CCJSqlParserUtil.parse(SQL_IN)).getSelectBody();
        InExpression inExpression = (InExpression) plainSelectIn.getWhere();
        ItemsList rightItemsList = inExpression.getRightItemsList();
        System.err.println(rightItemsList); // (8, 9, 10)

        // plainSelect.getDistinct();
        // plainSelect.getFetch();
        // plainSelect.getFirst();
        // plainSelect.getGroupBy();
        // .......
    }

    /**
     * Insert 解析
     *
     * @throws JSQLParserException
     */
    @Test
    public void testInsertParser() throws JSQLParserException {
        // 3.解析WHERE条件
        String insertSql = "INSERT INTO test ( c1,c2) VALUES ( 001,002)";
        Statement statement = CCJSqlParserUtil.parse(insertSql);
        if (statement instanceof Insert insert) {
            // 添加新列
            insert.addColumns(new Column("c3 "));
            // 添加新插入值
            ExpressionList expressionList = (ExpressionList) insert.getItemsList();
            expressionList.getExpressions().add(new StringValue("003"));
            System.err.println(insert); //INSERT INTO test (c1, c2, c3 ) VALUES (001, 002, '003')
        }
    }


    /**
     * Update 解析
     *
     * @throws JSQLParserException
     */
    @Test
    public void testUpdateParser() throws JSQLParserException {
        String updateSql = "UPDATE Person SET FirstName = 'Fred' WHERE LastName = 'Wilson' ";
        Statement statement = CCJSqlParserUtil.parse(updateSql);
        if (statement instanceof Update update) {
            Expression where = update.getWhere(); // 获取WHERE表达式=》LastName = 'Wilson'
            System.err.println(where);
            List<Column> columns = update.getColumns(); // 获取修改列=》 FirstName
            columns.forEach(System.out::println);
        }
    }

}
