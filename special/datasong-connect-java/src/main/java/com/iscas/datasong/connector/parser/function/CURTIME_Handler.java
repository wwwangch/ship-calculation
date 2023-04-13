package com.iscas.datasong.connector.parser.function;

import com.iscas.datasong.connector.util.DateSafeUtils;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Function;

import java.util.Date;
import java.util.Map;

/**
 * CURTIME() 返回当前时间
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/16 10:14
 * @since jdk11
 */
@SuppressWarnings({"AlibabaClassNamingShouldBeCamel", "unused"})
public class CURTIME_Handler implements FunctionHandler{
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        Date date = new Date();
        String dateStr = DateSafeUtils.format(date, "HH:mm:ss");
        if (alias != null) {
            data.put(alias.getName(), dateStr);
        } else {
            data.put(func.toString(), dateStr);
        }
    }
}
