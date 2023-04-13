package com.iscas.datasong.connector.parser.function;

import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Function;

import java.util.Date;
import java.util.Map;

/**
 * CURRENT_DATE() 返回当前日期
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/16 10:12
 * @since jdk11
 */
@SuppressWarnings({"AlibabaClassNamingShouldBeCamel", "unused"})
public class CURRENT_DATE_Handler implements FunctionHandler {
    public void handle(Map<String, Object> data, Alias alias, String funcName) {
        Date date = new Date();
        if (alias != null) {
            data.put(alias.getName(), date);
        } else {
            data.put(funcName, date);
        }
    }

    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {

    }
}
