package com.iscas.common.web.tools.jsonxml;

import com.iscas.common.web.tools.json.JsonUtils;
import com.iscas.common.web.tools.xml.XmlFormatter;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.json.XML;

import java.io.IOException;

/**
 * JSON-XML转换工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/1/20 15:46
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class JsonXmlConvertUtils {

    /**
     * 将json转为XML
     * */
    public static String json2Xml(String json, boolean withRoot) throws IOException {
        net.sf.json.xml.XMLSerializer xmlSerializer = new net.sf.json.xml.XMLSerializer();
        // 根节点名称
        xmlSerializer.setRootName("json2xml");
        // 不对类型进行设置
        xmlSerializer.setTypeHintsEnabled(false);
        String xmlStr;
        net.sf.json.JSONObject jobj = net.sf.json.JSONObject.fromObject(json);
        xmlStr = xmlSerializer.write(jobj);
        //去掉根节点
        if (!withRoot) {
            xmlStr = StringUtils.substringAfter(xmlStr, "<json2xml>");
            xmlStr = StringUtils.substringBeforeLast(xmlStr, "</json2xml>");
        }
        xmlStr = XmlFormatter.format(xmlStr);
        return xmlStr;

    }

    /**
    *  xml转为JSON
     * */
    public static String xml2Json(String xml) {
        JSONObject xmlJsonObj = XML.toJSONObject(xml);
        String jsonStr = xmlJsonObj.toString();
        jsonStr = JsonUtils.formatJson(jsonStr);
        return jsonStr;
    }

}
