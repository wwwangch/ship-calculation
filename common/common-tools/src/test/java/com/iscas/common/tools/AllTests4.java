package com.iscas.common.tools;

import com.iscas.common.tools.arithmetic.FloatExactArithUtilsTests;
import com.iscas.common.tools.assertion.AssertArrayUtilsTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


/**
 *  <p>Junit4所有的单元测试<p/>
 *  <p>大部分单元测试使用的Junit4，待升级至Junit5后再弃用此类<p/>
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/4/28 21:21
 * @since jdk1.8
 */

@RunWith(Suite.class)
@Deprecated
@SuiteClasses({FloatExactArithUtilsTests.class, AssertArrayUtilsTests.class})
public class AllTests4 {
}
