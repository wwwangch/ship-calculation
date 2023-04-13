package com.iscas.datasong.connector.parser.function;

import cn.hutool.core.collection.CollectionUtil;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * FIELD(s,s1,s2...),返回第一个字符串 s 在字符串列表(s1,s2...)中的位置,下标以1开始
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/7/28 10:42
 * @since jdk11
 */
@SuppressWarnings({"unused", "AlibabaClassNamingShouldBeCamel"})
public class FIELD_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        ExpressionList parameters = func.getParameters();
        if (parameters != null) {
            List<Expression> expressions = parameters.getExpressions();
            if (CollectionUtil.isNotEmpty(expressions)) {
                Expression exp1 = expressions.get(0);
                Object first = getData(data, exp1);
                int index = 0;
                if (first != null) {
                    List<String> strs = new ArrayList<>();
                    for (int i = 1; i < expressions.size(); i++) {
                        Expression expression = expressions.get(i);
                        Object data1 = getData(data, expression);
                        strs.add(data1 == null ? null : data1.toString());
                    }
                    String str = first.toString();
                    for (int i = 0; i < strs.size(); i++) {
                        if (str.equalsIgnoreCase(strs.get(i))) {
                            index = i + 1;
                            break;
                        }
                    }
                }
                if (alias != null) {
                    data.put(alias.getName(), index);
                } else {
                    data.put(func.toString(), index);
                }
            }
        }
    }

}
