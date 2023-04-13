package com.iscas.common.tools.constant;

import java.time.format.DateTimeFormatter;

/**
 * 时间常量
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/12/4 18:48
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public interface TimeConstant {

    DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

}
