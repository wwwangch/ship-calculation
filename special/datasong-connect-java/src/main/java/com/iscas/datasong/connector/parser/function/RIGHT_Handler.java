package com.iscas.datasong.connector.parser.function;

import cn.hutool.core.collection.CollectionUtil;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;

import java.util.List;
import java.util.Map;

/**
 * RIGHT(s,n) 返回字符串 s 的后 n 个字符
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/10 10:54
 * @since jdk11
 */
@SuppressWarnings({"JavadocDeclaration", "AlibabaClassNamingShouldBeCamel", "unused"})
public class RIGHT_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        ExpressionList parameters = func.getParameters();
        if (parameters != null) {
            List<Expression> expressions = parameters.getExpressions();
            if (CollectionUtil.isNotEmpty(expressions)) {
                Expression exp1 = expressions.get(0);
                Expression exp2 = expressions.get(1);
                Object first = getData(data, exp1);
                Object second = getData(data, exp2);
                String result = "";
                if (first != null && second != null) {
                    String str = first.toString();
                    int len = -1;
                    try {
                        len = Integer.parseInt(second.toString());
                    } catch (Exception ignored) {}
                    if (len > 0) {
                       if (len > str.length()) {
                           result = str;
                       } else {
                           result = str.substring(str.length() - len);
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
