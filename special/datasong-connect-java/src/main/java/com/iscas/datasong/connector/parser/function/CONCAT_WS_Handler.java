package com.iscas.datasong.connector.parser.function;

import cn.hutool.core.collection.CollectionUtil;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 同 CONCAT(x, s1,s2...sn) 函数，但是每个字符串之间要加上 x，x 可以是分隔符
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/7/28 10:42
 * @since jdk11
 */
@SuppressWarnings({"unused", "AlibabaClassNamingShouldBeCamel"})
public class CONCAT_WS_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        ExpressionList parameters = func.getParameters();
        if (parameters != null) {
            List<Expression> expressions = parameters.getExpressions();
            if (CollectionUtil.isNotEmpty(expressions)) {
                StringBuilder sb = new StringBuilder();
                Expression exp1 = expressions.get(0);
                Object first = getData(data, exp1);
                String res = null;
                if (first != null) {
                    List<String> concatStrs = new ArrayList<>();
                    for (int i = 1; i < expressions.size(); i++) {
                        Expression expression = expressions.get(i);
                        Object data1 = getData(data, expression);
                        if (data1 == null) {
                            concatStrs = null;
                            break;
                        }
                        concatStrs.add(data1.toString());
                    }
                    assert concatStrs != null;
                    res = concatStrs.stream().collect(Collectors.joining(first.toString()));
                }
                if (alias != null) {
                    data.put(alias.getName(), res);
                } else {
                    data.put(func.toString(), res);
                }
            }
        }
    }
}
