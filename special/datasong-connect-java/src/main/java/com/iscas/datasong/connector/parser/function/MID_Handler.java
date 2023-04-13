package com.iscas.datasong.connector.parser.function;

import cn.hutool.core.collection.CollectionUtil;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;

import java.util.List;
import java.util.Map;

/**
 * MID(s,n,len) 从字符串 s 的 n 位置截取长度为 len 的子字符串，同 SUBSTRING(s,n,len)
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/8
 * @since jdk11
 */
@SuppressWarnings({"unused", "AlibabaClassNamingShouldBeCamel", "JavadocDeclaration"})
public class MID_Handler implements FunctionHandler {
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
                String result = "";
                if (first != null && second != null && third != null) {
                   String str = first.toString();
                   int start = 0;
                   try {
                       start = Integer.parseInt(second.toString());
                   } catch (Exception ignored) {}
                   if (start > 0) {
                       int len = 0;
                       try {
                           len = Integer.parseInt(third.toString());
                       } catch (Exception ignored) {}
                       if (len > 0) {
                           if (start + len > str.length()) {
                               result = str.substring(start - 1);
                           } else {
                               result = str.substring(start - 1, start - 1 + len);
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
}
