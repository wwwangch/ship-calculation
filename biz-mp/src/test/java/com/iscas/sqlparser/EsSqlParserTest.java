package com.iscas.sqlparser;

import com.iscas.common.tools.sql.SqlParserUtils;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.deparser.ExpressionDeParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/2/18 14:26
 * @since jdk1.8
 */
public class EsSqlParserTest {

    /**
     * 单条件查询
     * */
    @Test
    public void test1() throws JSQLParserException {
        String sql = "SELECT * FROM book_info where name = 'hello'";
        List<String> tables = SqlParserUtils.selectTable(sql);
        System.out.printf("表名：%s\n", tables);
        Assertions.assertEquals(tables, List.of("book_info"));

        String where = SqlParserUtils.selectWhere(sql);
        System.out.printf("where条件：%s\n", where);
        Assertions.assertEquals("name = 'hello'", where);

        Map<Object, Object> whereParseResult = SqlParserUtils.fullResolutionWhere(where);
        System.out.printf("where解析条件：%s\n", whereParseResult);
        Assertions.assertEquals("{operate==, leftExpression=name, rightExpression='hello'}", whereParseResult.toString());

        List<String> selectItems = SqlParserUtils.selectItems(sql);
        System.out.printf("查询条件：%s\n", selectItems);
        Assertions.assertEquals(selectItems, List.of("*"));
    }


    /**
     * 多条件查询
     * */
    @Test
    public void test2() throws JSQLParserException {
        String sql = "SELECT * FROM book_info where name = 'hello' and author='tom' and id =1";
        List<String> tables = SqlParserUtils.selectTable(sql);
        System.out.printf("表名：%s\n", tables);
        Assertions.assertEquals(tables, List.of("book_info"));

        CCJSqlParserManager parserManager = new CCJSqlParserManager();
        Select select = (Select) parserManager.parse(new StringReader(sql));
        PlainSelect plain = (PlainSelect) select.getSelectBody();
        Expression where = plain.getWhere();
        ExpressionDeParser expressionDeParser = new ExpressionDeParser();
        where.accept(expressionDeParser);

        System.out.println(where);

    }

}
