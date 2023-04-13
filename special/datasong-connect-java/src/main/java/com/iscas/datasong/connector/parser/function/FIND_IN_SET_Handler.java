package com.iscas.datasong.connector.parser.function;

import cn.hutool.core.collection.CollectionUtil;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * FIND_IN_SET(s1,s2) 返回在字符串s2中与s1匹配的字符串的位置
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/7/29 17:42
 * @since jdk11
 */
@SuppressWarnings({"unused", "AlibabaClassNamingShouldBeCamel"})
public class FIND_IN_SET_Handler implements FunctionHandler {
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
                int index = 0;
                if (first != null && second != null) {
                    String[] strs = second.toString().split(",");
                    for (int i = 0; i < strs.length; i++) {
                        if (Objects.equals(first.toString(), strs[i].trim())) {
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
