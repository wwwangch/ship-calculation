package com.iscas.datasong.connector.parser.function;

import cn.hutool.core.collection.CollectionUtil;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;

import java.util.List;
import java.util.Map;

/**
 * TRIM(s) 去掉字符串 s 开始和结尾处的空格
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/10 15:00
 * @since jdk11
 */
@SuppressWarnings({"unused", "AlibabaClassNamingShouldBeCamel"})
public class TRIM_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        ExpressionList parameters = func.getParameters();
        if (parameters != null) {
            List<Expression> expressions = parameters.getExpressions();
            if (CollectionUtil.isNotEmpty(expressions)) {
                Expression exp1 = expressions.get(0);
                Object first = getData(data, exp1);
                String result = null;
                if (first != null) {
                   result = first.toString().trim();
                }
                if (alias != null) {
                    data.put(alias.getName(), result);
                } else {
                    data.put(func.toString(), result);
                }
            }
        }
    }

    protected String ltrim(String str) {
        char[] chars = str.toCharArray();
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (aChar != ' ') {
                index = i;
                break;
            }
        }
        return str.substring(index);
    }

}
