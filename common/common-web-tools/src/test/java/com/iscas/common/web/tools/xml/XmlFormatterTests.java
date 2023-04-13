package com.iscas.common.web.tools.xml;

import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/1/20 16:22
 * @since jdk1.8
 */
public class XmlFormatterTests {
    @Test
    public void test() throws IOException {
        String s = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><PARAM><DBID>35</DBID><SEQUENCE>atgtca</SEQUENCE><MAXNS>10</MAXNS><MINIDENTITIES>90</MINIDENTITIES><MAXEVALUE>10</MAXEVALUE><USERNAME>admin</USERNAME><PASSWORD>111111</PASSWORD><TYPE>P</TYPE><RETURN_TYPE>2</RETURN_TYPE></PARAM>";//未格式化前的xml
        System.out.println(new XmlFormatter().format(s));
    }
}
