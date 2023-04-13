package com.iscas.common.web.tools;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/5/7 22:02
 * @since jdk1.8
 */

import com.iscas.common.web.tools.json.JsonTests;
import com.iscas.common.web.tools.json.JsonUtilsTests;
import com.iscas.common.web.tools.jsonxml.JsonXmlConvertUtilsTests;
import com.iscas.common.web.tools.xml.XmlFormatterTests;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({
        JsonTests.class, JsonUtilsTests.class,
        JsonXmlConvertUtilsTests.class, XmlFormatterTests.class
})
public class AllTests {
}
