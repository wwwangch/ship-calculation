package com.iscas.datasong.connector.parser.function;

import cn.hutool.core.collection.CollectionUtil;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;

import java.util.List;
import java.util.Map;

/**
 * REPLACE(s,s1,s2) 将字符串 s2 替代字符串 s 中的字符串 s1
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/10 10:47
 * @since jdk11
 */
@SuppressWarnings({"JavadocDeclaration", "AlibabaClassNamingShouldBeCamel", "unused"})
public class REPLACE_Handler implements FunctionHandler {
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
                    result = first.toString().replace(second.toString(), third.toString());
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
