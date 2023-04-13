//package com.iscas.biz.service.common;
//
//import com.iscas.biz.BizApp;
//import com.iscas.biz.domain.common.User;
//import com.iscas.biz.mapper.common.UserMapper;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Arrays;
//import java.util.List;
//
///**
// * UserService Tester.
// *
// * @author <Authors name>
// * @version 1.0
// * @since <pre>5月 25, 2021</pre>
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = BizApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class UserServiceTest {
//    @Autowired
//    private UserMapper userMapper;
//
//    @Before
//    public void before() throws Exception {
//    }
//
//    @After
//    public void after() throws Exception {
//    }
//
//    /**
//     * Method: deleteCache(List<Object> ids)
//     */
//    @Test
//    public void testDeleteCache() throws Exception {
//        List<Object> ids = Arrays.asList(1, 2, 3);
//        List<User> users = userMapper.selectUserByIds(ids);
//        users.forEach(System.out::println);
//    }
//
//}
