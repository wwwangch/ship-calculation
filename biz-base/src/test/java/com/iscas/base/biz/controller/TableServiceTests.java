//package com.iscas.base.biz.controller;
//
//import com.iscas.base.biz.model.auth.User;
//import com.iscas.base.biz.service.common.TableService;
//import com.iscas.base.biz.util.TableInfoUtils;
//import com.iscas.common.tools.core.security.MD5Utils;
//import com.iscas.templet.view.table.TableHeaderResponseData;
//import org.junit.Assert;
//import org.junit.Ignore;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// *
// *
// * @author zhuquanwen
// * @version 1.0
// * @date 2018/9/6 16:52
// * @since jdk1.8
// */
////@RunWith(SpringRunner.class)
////@SpringBootTest(classes = webWebApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
////@EnableAutoConfiguration
//public class TableServiceTests {
//    @Autowired
//    private TableService tableService;
//
////    @Test
//    public void test1() throws Exception {
//        User user = new User();
//        user.setUsername("test_table_service");
//        user.setPassword(MD5Utils.saltMD5("test_table_service"));
//        TableHeaderResponseData tableHeaderResponseData = TableInfoUtils.getTableHeader("user");
//        Object id = tableService.saveForMysql(user, "user", tableHeaderResponseData, false, null);
//        System.out.println(id);
//        Assert.assertNotNull(id);
//    }
//
////    @Test
//    @Ignore
//    public void test2() throws Exception {
//        User user = new User();
//        user.setPassword("erhhhhhhhhhhhhhh");
//        user.setPassword(MD5Utils.saltMD5("test_table_service"));
//        Map map = new HashMap<>();
//        map.put("type", "apply");
//        TableHeaderResponseData tableHeaderResponseData = TableInfoUtils.getTableHeader("user");
//        Object id = tableService.saveForMysql(user, "user", tableHeaderResponseData, false, null);
//        System.out.println(id);
//        Assert.assertNotNull(id);
//    }
//}
