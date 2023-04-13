package com.iscas.datasong.connector.parser.function;

import cn.hutool.core.collection.CollectionUtil;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>SUBSTRING_INDEX(s, delimiter, number) 返回从字符串 s 的第 number 个出现的分隔符 delimiter 之后的子串。</p>
 * <p>如果 number 是正数，返回第 number 个字符左边的字符串。</p>
 * <p>如果 number 是负数，返回第(number 的绝对值(从右边数))个字符右边的字符串。</p>
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/10 14:01
 * @since jdk11
 */
@SuppressWarnings({"JavadocDeclaration", "AlibabaClassNamingShouldBeCamel", "unused"})
public class SUBSTRING_INDEX_Handler implements FunctionHandler {
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
                    String[] splitStrArray = first.toString().split(escape(second.toString()));
                    int len = 0;
                    try {
                        len = Integer.parseInt(third.toString());
                    } catch (Exception ignored){}

                    if (len > 0) {
                        int end = Math.min(len, splitStrArray.length);
                        result = Arrays.stream(Arrays.copyOfRange(splitStrArray, 0, end)).collect(Collectors.joining(second.toString()));
                    } else if (len < 0) {
                        int start = Math.max(0, splitStrArray.length + len);
                        result = Arrays.stream(Arrays.copyOfRange(splitStrArray, start, splitStrArray.length)).collect(Collectors.joining(second.toString()));
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
