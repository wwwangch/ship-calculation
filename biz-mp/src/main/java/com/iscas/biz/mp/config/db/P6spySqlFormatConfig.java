package com.iscas.biz.mp.config.db;

import com.iscas.common.tools.core.date.DateSafeUtils;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * 自定义 p6spy sql输出格式
 *
 * @author admin
 */
public class P6spySqlFormatConfig implements MessageFormattingStrategy {

    /**
     * 过滤掉定时任务的 SQL
     */
    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        //noinspection RegExpSimplifiable
        return StringUtils.isNotBlank(sql) ? DateSafeUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss")
                + " | 耗时 " + elapsed + " ms | SQL 语句：" + StringUtils.LF + sql.replaceAll("[\\s]+", StringUtils.SPACE) + ";" : "";
    }
}
