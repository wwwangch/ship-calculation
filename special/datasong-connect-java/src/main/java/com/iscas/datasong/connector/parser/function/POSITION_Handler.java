package com.iscas.datasong.connector.parser.function;

import cn.hutool.core.collection.CollectionUtil;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.relational.NamedExpressionList;

import java.util.List;
import java.util.Map;

/**
 * POSITION(s1 IN s) 从字符串 s 中获取 s1 的开始位置
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/9 18:44
 * @since jdk11
 */
@SuppressWarnings({"JavadocDeclaration", "AlibabaClassNamingShouldBeCamel", "unused"})
public class POSITION_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        NamedExpressionList parameters = func.getNamedParameters();
        if (parameters != null) {
            List<Expression> expressions = parameters.getExpressions();
            if (CollectionUtil.isNotEmpty(expressions)) {
                Expression exp1 = expressions.get(0);
                Expression exp2 = expressions.get(1);
                Object first = getData(data, exp1);
                Object second = getData(data, exp2);
                int result = 0;
                if (first != null && second != null) {
                    String str1 = first.toString();
                    String str2 = second.toString();
                    int index = str2.indexOf(str1);
                    if (index >= 0) {
                        result = index + 1;
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
