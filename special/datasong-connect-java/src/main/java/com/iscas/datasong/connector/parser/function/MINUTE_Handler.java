package com.iscas.datasong.connector.parser.function;

import cn.hutool.core.collection.CollectionUtil;
import com.iscas.datasong.connector.util.DateSafeUtils;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * MINUTE(t) 返回 t 中的分钟值
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/17 8:49
 * @since jdk11
 */
@SuppressWarnings({"AlibabaClassNamingShouldBeCamel", "unused"})
public class MINUTE_Handler implements FunctionHandler{
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
                    String str = first.toString();
                    String[] strs = str.split(":");
                    if (strs.length > 2) {
                        try {
                            result = String.valueOf(Integer.parseInt(strs[1]));
                        } catch (Exception ignored) {
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
