package com.iscas.datasong.connector.parser.function;

import cn.hutool.core.collection.CollectionUtil;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;

import java.util.List;
import java.util.Map;

/**
 * SUBSTR(s, start, length)	 	从字符串 s 的 start 位置截取长度为 length 的子字符串
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/10 13:50
 * @since jdk11
 */
@SuppressWarnings({"JavadocDeclaration", "AlibabaClassNamingShouldBeCamel", "unused"})
public class SUBSTR_Handler implements FunctionHandler {
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
                    int start = 0;
                    try {
                        start = Integer.parseInt(second.toString());
                    } catch (Exception ignored) {}
                    if (start > 0) {
                        start--;
                        int len = 0;
                        try {
                            len = Integer.parseInt(third.toString());
                        } catch (Exception ignored) {}
                        if (len > 0) {
                            char[] chars = first.toString().toCharArray();
                            StringBuilder sb = new StringBuilder();
                            for (int i = start; i < chars.length && i < start + len; i++) {
                                sb.append(chars[i]);
                            }
                            result = sb.toString();
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
