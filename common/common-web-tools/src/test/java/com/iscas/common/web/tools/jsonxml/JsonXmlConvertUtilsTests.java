package com.iscas.common.web.tools.jsonxml;

import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/1/20 15:52
 * @since jdk1.8
 */
public class JsonXmlConvertUtilsTests {

    /**
     * json转为XML
     * */
    @Test
    public void test0() throws IOException {
        String s = JsonXmlConvertUtils.json2Xml("{\n" +
                "\t\"Proxy\": {\n" +
                "\t\t\"defaultSettings\": {\n" +
                "\t\t\t\"selfWebPath\": \"/sp/\",\n" +
                "\t\t\t\"maxContentLength\": 1073741824,\n" +
                "\t\t\t\"basePath\": \"/\",\n" +
                "\t\t\t\"staticResourcePath\": \"F:/static\",\n" +
                "\t\t\t\"port\": 8180,\n" +
                "\t\t\t\"SO_BACKLOG\": 2097152,\n" +
                "\t\t\t\"sslKeyPath\": \"C:/Users/Administrator/local.jks\"\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}", false);
        System.out.println(s);
    }


    /**
     * xml转为json
     * */
    @Test
    public void test() throws IOException {
        String s = JsonXmlConvertUtils.xml2Json("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Proxy>\n" +
                "\t<defaultSettings>\n" +
                "\t\t<port>8180</port>\n" +
                "\t\t<basePath>/</basePath>\n" +
                "\t\t<selfWebPath>/sp/</selfWebPath>\n" +
                "\t\t<SO_BACKLOG>2097152</SO_BACKLOG>\n" +
                "\t\t<maxContentLength>1073741824</maxContentLength>\n" +
                "\t\t<!--<maxContentLength>102400000</maxContentLength>-->\n" +
                "\t\t<sslKeyPath>C:/Users/Administrator/local.jks</sslKeyPath>\n" +
                "\t\t<staticResourcePath>F:/static</staticResourcePath>\n" +
                "\t</defaultSettings>\n" +
                "</Proxy>");
        System.out.println(s);
    }
}
