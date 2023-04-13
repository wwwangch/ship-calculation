package com.iscas.datasong.connector.parser.function;

import cn.hutool.core.collection.CollectionUtil;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;

import java.util.List;
import java.util.Map;

/**
 * INSERT(s1,x,len,s2)	字符串 s2 替换 s1 的 x 位置开始长度为 len 的字符串
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/7/29 17:42
 * @since jdk11
 */
@SuppressWarnings({"unused", "AlibabaClassNamingShouldBeCamel"})
public class INSERT_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        ExpressionList parameters = func.getParameters();
        if (parameters != null) {
            List<Expression> expressions = parameters.getExpressions();
            if (CollectionUtil.isNotEmpty(expressions)) {
                Expression exp1 = expressions.get(0);
                Expression exp2 = expressions.get(1);
                Expression exp3 = expressions.get(2);
                Expression exp4 = expressions.get(3);
                Object first = getData(data, exp1);
                Object second = getData(data, exp2);
                Object third = getData(data, exp3);
                Object fourth = getData(data, exp4);
                Object result = null;
                if (first != null && second != null && third != null && fourth != null) {
                    int index = 0;
                    try {
                        index = Integer.parseInt(second.toString());
                    } catch (Exception ignored) {
                    }
                    if (index > 0) {
                        int len = 0;
                        try {
                            len = Integer.parseInt(third.toString());
                        } catch (Exception ignored) {
                        }
                        String front = "";
                        String end = "";
                        if (index > 0) {
                            if (index < first.toString().length()) {
                                front = first.toString().substring(0, index - 1);
                            } else {
                                front = first.toString();
                            }
                        }
                        int last = len + index - 1;
                        if (last < first.toString().length()) {
                            end = first.toString().substring(last);
                        }
                        result = front + fourth + end;
                    } else {
                        result = first.toString();
                    }
                }
                if (alias != null) {
                    data.put(alias.getName(), result);
                } else {
                    data.put(func.toString(), result);
                }
            }
        }
    }

}
