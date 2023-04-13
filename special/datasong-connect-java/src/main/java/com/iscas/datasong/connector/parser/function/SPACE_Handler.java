package com.iscas.datasong.connector.parser.function;

import cn.hutool.core.collection.CollectionUtil;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;

import java.util.List;
import java.util.Map;

/**
 * SPACE(s) 返回n个空格
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/10 11:13
 * @since jdk11
 */
@SuppressWarnings({"JavadocDeclaration", "AlibabaClassNamingShouldBeCamel", "unused"})
public class SPACE_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        ExpressionList parameters = func.getParameters();
        if (parameters != null) {
            List<Expression> expressions = parameters.getExpressions();
            if (CollectionUtil.isNotEmpty(expressions)) {
                Expression exp1 = expressions.get(0);
                Object first = getData(data, exp1);
                String result = "";
                if (first != null) {
                    int spaceCount = -1;
                    try {
                        spaceCount = Integer.parseInt(first.toString());
                    } catch (Exception ignored) {}
                    if (spaceCount > 0) {
                        result = " ".repeat(spaceCount);
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
