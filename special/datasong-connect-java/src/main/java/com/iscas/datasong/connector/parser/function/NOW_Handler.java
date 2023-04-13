package com.iscas.datasong.connector.parser.function;

import com.iscas.datasong.connector.util.DateSafeUtils;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Function;

import java.util.Date;
import java.util.Map;

/**
 * NOW() 返回当前日期和时间
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/17 8:17
 * @since jdk11
 */
@SuppressWarnings({"unused", "AlibabaClassNamingShouldBeCamel"})
public class NOW_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        Object result = DateSafeUtils.format(new Date());
        if (alias != null) {
            data.put(alias.getName(), result);
        } else {
            data.put(func.toString(), result);
        }

    }
}
