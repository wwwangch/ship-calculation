package com.iscas.datasong.connector.parser.function;

import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Function;

import java.util.Map;

/**
 * EXTRACT(type FROM d)
 *
 * 从日期 d 中获取指定的值，type 指定返回的值。
 * type可取值为：
 * MICROSECOND
 * SECOND
 * MINUTE
 * HOUR
 * DAY
 * WEEK
 * MONTH
 * QUARTER
 * YEAR
 * SECOND_MICROSECOND
 * MINUTE_MICROSECOND
 * MINUTE_SECOND
 * HOUR_MICROSECOND
 * HOUR_SECOND
 * HOUR_MINUTE
 * DAY_MICROSECOND
 * DAY_SECOND
 * DAY_MINUTE
 * DAY_HOUR
 * YEAR_MONTH
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/16 16:24
 * @since jdk11
 */
@SuppressWarnings({"unused", "AlibabaClassNamingShouldBeCamel"})
public class EXTRACT_Handler implements FunctionHandler{
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        throw new UnsupportedOperationException("暂不支持方法extract");
    }
}
