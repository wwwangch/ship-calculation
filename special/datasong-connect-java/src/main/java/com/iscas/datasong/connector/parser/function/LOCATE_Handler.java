package com.iscas.datasong.connector.parser.function;

import cn.hutool.core.collection.CollectionUtil;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;

import java.util.List;
import java.util.Map;

/**
 * LOCATE(s1,s) 从字符串 s 中获取 s1 的开始位置
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/7/29 17:42
 * @since jdk11
 */
@SuppressWarnings({"unused", "AlibabaClassNamingShouldBeCamel", "JavadocDeclaration"})
public class LOCATE_Handler implements FunctionHandler {
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
                Integer result = null;
                if (first != null && second != null) {
                    if (!second.toString().contains(first.toString())) {
                        result = 0;
                    } else {
                        result = second.toString().indexOf(first.toString()) + 1;
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
