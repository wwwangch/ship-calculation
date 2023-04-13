package com.iscas.datasong.connector.parser.function;

import cn.hutool.core.collection.CollectionUtil;
import com.iscas.datasong.connector.util.DateSafeUtils;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/17 17:37
 * @since jdk11
 */
@SuppressWarnings({"AlibabaClassNamingShouldBeCamel", "unused"})
public class TIMESTAMP_Handler implements FunctionHandler{
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        ExpressionList parameters = func.getParameters();
        if (parameters != null) {
            List<Expression> expressions = parameters.getExpressions();
            if (CollectionUtil.isNotEmpty(expressions)) {
                Object result = null;
                Expression exp1 = expressions.get(0);
                Expression exp2 = expressions.get(1);
                Object first = getData(data, exp1);
                Object second = getData(data, exp2);
                if (first != null) {
                    if (second == null) {
                        Date date = getDate(first);
                        result = DateSafeUtils.format(date);
                    } else {
                        Date date = getDate(first);
                        String[] strs = second.toString().split(":");
                        long offset = (Integer.parseInt(strs[0].trim()) * 3600L +  Integer.parseInt(strs[1].trim()) * 60L +
                                Integer.parseInt(strs[2].trim())) * 1000L;
                        result = DateSafeUtils.format(new Date(date.getTime() + offset));
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
}
