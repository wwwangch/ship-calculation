package com.iscas.datasong.connector.parser.function;

import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Function;

import java.util.Map;

/**
 * MICROSECOND(date) 返回日期参数所对应的微秒数
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/17 8:44
 * @since jdk11
 */
@SuppressWarnings({"AlibabaClassNamingShouldBeCamel", "unused"})
public class MICROSECOND_Handler implements FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        throw new UnsupportedOperationException("不支持的方法MICROSECOND");
    }
}
