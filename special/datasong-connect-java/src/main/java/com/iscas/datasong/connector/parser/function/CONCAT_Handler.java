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
 * 字符串拼接
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/7/28 10:42
 * @since jdk11
 */
@SuppressWarnings({"unused", "AlibabaClassNamingShouldBeCamel"})
public class CONCAT_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        ExpressionList parameters = func.getParameters();
        if (parameters != null) {
            List<Expression> expressions = parameters.getExpressions();
            if (CollectionUtil.isNotEmpty(expressions)) {
                StringBuilder sb = new StringBuilder();
                for (Expression expression : expressions) {
                    if (expression instanceof Column) {
                        Column column = (Column) expression;
                        String columnName = column.getColumnName();
                        if (isStr(columnName)) {
                            columnName = columnName.substring(1, columnName.length() - 1);
                            sb.append(columnName);
                        } else {
                            if (data.containsKey(columnName)) {
                                sb.append(data.get(columnName));
                            } else {
                                sb = null;
                                break;
                            }
                        }
                    } else if (expression instanceof StringValue) {
                        sb.append(((StringValue) expression).getValue());
                    } else {
                        throw new RuntimeException(String.format("不支持的Expression类型:[%s]", expression.getClass().getName()));
                    }
                }
                if (sb != null) {
                    if (alias != null) {
                        data.put(alias.getName(), sb.toString());
                    } else {
                        data.put(func.toString(), sb.toString());
                    }
                }
            }
        }
    }
}
