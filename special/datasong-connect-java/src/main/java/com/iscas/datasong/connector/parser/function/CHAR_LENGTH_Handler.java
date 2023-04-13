package com.iscas.datasong.connector.parser.function;

import cn.hutool.core.collection.CollectionUtil;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.schema.Column;

import java.util.List;
import java.util.Map;

/**
 * 返回字符串的字符数函数
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/7/28 10:42
 * @since jdk11
 */
@SuppressWarnings({"unused", "AlibabaClassNamingShouldBeCamel"})
public class CHAR_LENGTH_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        ExpressionList parameters = func.getParameters();
        if (parameters != null) {
            List<Expression> expressions = parameters.getExpressions();
            if (CollectionUtil.isNotEmpty(expressions)) {
                Expression expression = expressions.get(0);
                if (expression instanceof Column) {
                    Column column = (Column) expression;
                    String columnName = column.getColumnName();
                    String str;
                    if (isStr(columnName)) {
                        columnName = columnName.substring(1, columnName.length() - 1);
                        str = columnName;
                    } else {
                        Object o = data.get(column.getColumnName());
                        if (o instanceof String) {
                            str = (String) o;
                        } else {
                            return;
                        }
                    }

                    // 如果列为空，char_length 也为空
                    Integer length = str.length();
                    if (alias != null) {
                        data.put(alias.getName(), length);
                    } else {
                        data.put(func.toString(), length);
                    }
                } else if (expression instanceof StringValue) {
                    StringValue stringValue = (StringValue) expression;
                    Integer length = stringValue.getValue().length();
                    if (alias != null) {
                        data.put(alias.getName(), length);
                    } else {
                        data.put(func.toString(), length);
                    }
                }
            }
        }
    }
}
