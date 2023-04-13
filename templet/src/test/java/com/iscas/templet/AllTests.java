package com.iscas.templet;

import com.iscas.templet.dynamic.TestBean;
import com.iscas.templet.helper.HeaderHelperTests;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/5/7 22:07
 * @since jdk1.8
 */
@RunWith(JUnitPlatform.class)
@SelectClasses({
        TestBean.class, HeaderHelperTests.class
})
public class AllTests {
}
