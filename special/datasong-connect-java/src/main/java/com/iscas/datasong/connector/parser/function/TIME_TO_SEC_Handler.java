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
 * TIME_TO_SEC(t) 	将时间 t 转换为秒
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/17 17:27
 * @since jdk11
 */
@SuppressWarnings({"unused", "AlibabaClassNamingShouldBeCamel"})
public class TIME_TO_SEC_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        ExpressionList parameters = func.getParameters();
        if (parameters != null) {
            List<Expression> expressions = parameters.getExpressions();
            if (CollectionUtil.isNotEmpty(expressions)) {
                Expression exp1 = expressions.get(0);
                Object first = getData(data, exp1);
                int result = 0;
                if (first != null) {
                    Date date = getTime(first);
                    String str = DateSafeUtils.format(date, "HH:mm:ss");
                    String[] strs = str.split(":");
                    result = Integer.parseInt(strs[0].trim()) * 3600 +  Integer.parseInt(strs[1].trim()) * 60 +
                            Integer.parseInt(strs[2].trim());
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
