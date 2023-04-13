//package com.iscas.base.biz.util;
//
//import com.iscas.common.web.tools.json.JsonUtils;
//import com.iscas.templet.exception.BaseException;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.JUnit4;
//
//@RunWith(JUnit4.class)
//public class JsonUtilsTests {
//
//    /**
//     *
//     * 测试向JSON中追加参数
//     * */
//    @Test
//    public void test1() {
//        try {
//            String s = JsonUtils.appendJson("{\"name\": \"zhangsan\"}", new Object[]{"age", 16});
//            Assert.assertNotNull(s);
//        } catch (BaseException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
