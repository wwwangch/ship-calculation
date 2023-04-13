package com.iscas.datasong.connector.parser.function;

import com.iscas.datasong.connector.util.DateSafeUtils;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Function;

import java.util.Date;
import java.util.Map;

/**
 * CURRENT_TIMESTAMP() 返回当前日期和时间
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/16 10:17
 * @since jdk11
 */
@SuppressWarnings({"AlibabaClassNamingShouldBeCamel", "unused"})
public class CURRENT_TIMESTAMP_Handler implements  FunctionHandler {
    @Override
    public void handle(Map<String, Object> data, Alias alias, Function func) {
        String dateStr = DateSafeUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        if (alias != null) {
            data.put(alias.getName(), dateStr);
        } else {
            data.put(func.toString(), dateStr);
        }
    }
}
