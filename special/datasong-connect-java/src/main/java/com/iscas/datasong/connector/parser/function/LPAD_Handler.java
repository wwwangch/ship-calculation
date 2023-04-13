package com.iscas.datasong.connector.parser.function;

import cn.hutool.core.collection.CollectionUtil;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;

import java.util.List;
import java.util.Map;

/**
 * LPAD(s1,len,s2) 在字符串 s1 的开始处填充字符串 s2，使字符串长度达到 len
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/1
 * @since jdk11
 */
@SuppressWarnings({"unused", "AlibabaClassNamingShouldBeCamel"})
public class LPAD_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        ExpressionList parameters = func.getParameters();
        if (parameters != null) {
            List<Expression> expressions = parameters.getExpressions();
            if (CollectionUtil.isNotEmpty(expressions)) {
                Expression exp1 = expressions.get(0);
                Expression exp2 = expressions.get(1);
                Expression exp3 = expressions.get(2);
                Object first = getData(data, exp1);
                Object second = getData(data, exp2);
                Object third = getData(data, exp3);
                String result = null;
                if (first != null && second != null && third != null) {
                    int len = 0;
                    try {
                        len = Integer.parseInt(second.toString());
                    } catch (Exception ignored) {
                    }
                    if (len <= 0) {
                        result = "";
                    } else {
                        String s1 = first.toString();
                        String s2 = third.toString();
                        if (s1.length() > len) {
                            result = s1.substring(0, len);
                        } else {
                            if (len - s1.length() > s2.length()) {
                                result = getStr(s2, len - s1.length()) + s1;
                            } else {
                                result = s2.substring(0, len - s1.length()) + s1;
                            }
                        }
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

    protected String getStr(String str, int len) {
        return str.repeat(len / str.length()) + str.substring(0, len % str.length());
    }

}
