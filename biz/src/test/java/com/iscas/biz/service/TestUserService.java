//package com.iscas.biz.service;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.iscas.biz.BizApp;
//import com.iscas.base.mp.mapper.DynamicMapper;
//import com.iscas.biz.model.User;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.*;
//
///**
// * Mybatis-Plus 单元测试
// *
// * @author zhuquanwen
// * @version 1.0
// * @date 2018/10/12
// * @since jdk1.8
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = BizApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@EnableAutoConfiguration
//public class TestUserService {
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private DynamicMapper dynamicMapper;
//    /*测试查询全部*/
//    @Test
//    public void findAll(){
//        List<User> list = userService.list(null);
//        if(list != null) list.stream().forEach(System.out::println);
//    }
//
//
//
//    /*测试分页查询*/
//    @Test
//    public void t2() {
//        IPage<User> page = userService.page(new Page<User>(0, 12), null);
//        List<User> records = page.getRecords();
//        if(records != null) records.stream().forEach(System.out::println);
//    }
//
//    /*分页查询返回Map*/
//    @Test
//    public void t23() {
//        IPage<Map<String, Object>> mapIPage = userService.pageMaps(new Page<User>(2, 12), null);
//        System.out.println(mapIPage);
//    }
//
//    /*测试插入*/
//    @Test
//    public void t3() {
//        User user = new User();
//        user.setPassword(UUID.randomUUID().toString());
//        user.setPhone("121");
////        user.setRealname("221");
//        user.setUsername("zqw" + new Random().nextInt(100));
//        userService.save(user);
//        System.out.println(user);
//    }
//
//    /*测试批量插入*/
//    @Test
//    public void t4() {
//        List<User> users = new ArrayList<>();
//        for (int i = 0; i < 100 ; i++) {
//            User user = new User();
//            user.setPassword(UUID.randomUUID().toString());
//            user.setPhone("121");
////            user.setRealname("221");
//            user.setUsername("zqw" + new Random().nextInt(100));
//            users.add(user);
//        }
//        userService.saveBatch(users);
//        if(users != null) users.stream().forEach(System.out::println);
//    }
//
//    /*测试插入ID回传*/
//    @Test
//    public void t5() {
//        User user = new User();
//        user.setPassword(UUID.randomUUID().toString());
//        user.setPhone("121");
////        user.setRealname("221");
//        user.setUsername("zqw" + new Random().nextInt(100));
//        userService.save(user);
//        System.out.println(user.getId());
//    }
//
//    /*保存或修改*/
//    @Test
//    public void t25() {
//        User user = userService.getById(1);
//        user.setId(null);
//        user.setUsername("admin");
//        boolean b = userService.saveOrUpdate(user);
//        System.out.println(b);
//    }
//
//    /*批量保存或修改*/
//    @Test
//    public void t26() {
//        List<User> users = new ArrayList<>();
//        for (int i = 0; i < 10 ; i++) {
//            User user = userService.getById(1);
//            user.setId(null);
//            user.setUsername("admin" + new Random().nextInt(20));
//            users.add(user);
//        }
//        boolean b = userService.saveOrUpdateBatch(users);
//        if(users != null) users.stream().forEach(System.out::println);
//        System.out.println(b);
//    }
//
//    /*测试ID查询*/
//    @Test
//    public void t6() {
//        User user = userService.getById(1);
//        System.out.println(user);
//    }
//    /*测试按照ID删除*/
//    @Test
//    public void t7() {
//        boolean b = userService.removeById(6);
//        System.out.println(b);
//    }
//
//    /*测试按照多个ID删除*/
//    @Test
//    public void t8() {
//        boolean b = userService.removeByIds(Arrays.asList(2, 3, 4));
//        System.out.println(b);
//    }
//
//    /*按照查询条件删除*/
//    @Test
//    public void t22() {
//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.eq("id", 101);
//        boolean remove = userService.remove(queryWrapper);
//        System.out.println(remove);
//    }
//
//    /*按照Map查询条件删除*/
//    @Test
//    public void t24() {
//        Map map = new HashMap();
//        map.put("id","101");
//        boolean remove = userService.removeByMap(map);
//        System.out.println(remove);
//    }
//
//    /*测试按照ID修改*/
//    @Test
//    public boolean t9() {
//        User user = userService.getById(73);
////        user.setRealname("myrealname");
//        return userService.updateById(user);
//    }
//
//    /*测试按照条件更新*/
//    @Test
//    public void t10() {
//        User user = new User();
//        user.setPassword(UUID.randomUUID().toString());
//        user.setPhone("121444");
////        user.setRealname("221");
//        user.setUsername("zqw" + new Random().nextInt(100));
//        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
//        updateWrapper.set("realname", "xxx");
//        boolean update = userService.update(user, updateWrapper);
//        System.out.println(update);
//    }
//
//    /*测试按照条件更新*/
//    @Test
//    public void t11() {
//        User user = new User();
//        user.setPassword(UUID.randomUUID().toString());
//        user.setPhone("121444");
////        user.setRealname("221");
//        user.setUsername("zqw" + new Random().nextInt(100));
//        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
//        updateWrapper.between("id",34,78);
//        boolean update = userService.update(user, updateWrapper);
//        System.out.println(update);
//    }
//    /*测试按照条件查询*/
//    @GetMapping("/t12")
//    public void t12() {
//        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
//        userQueryWrapper.eq("id", 5);
//        userQueryWrapper.or(q -> q.like("username","zqw8"));
//        List<User> users = userService.list(userQueryWrapper);
//        if (users != null) users.stream().forEach(System.out::println);
//    }
//
//    /*groupby order by 等操作*/
//    @Test
//    public void t13() {
//        QueryWrapper userQueryWrapper = new QueryWrapper();
//        userQueryWrapper.groupBy("username");
//        userQueryWrapper.orderByDesc("username");
//        userQueryWrapper.select(" username, count(username) AS count");
//        List list = userService.listMaps(userQueryWrapper);
//        if (list != null) list.stream().forEach(System.out::println);
//    }
//
//    /*按照条件计数count操作*/
//    @Test
//    public void t14() {
//        QueryWrapper userQueryWrapper = new QueryWrapper();
//        userQueryWrapper.like("username","zqw2");
//        int count = userService.count(userQueryWrapper);
//        System.out.println(count);
//    }
//
//    /*按照条件查询返回Map,只返回一条值*/
//    @Test
//    public void t15() {
//        QueryWrapper userQueryWrapper = new QueryWrapper();
//        userQueryWrapper.like("username","zqw2");
//        Map map = userService.getMap(userQueryWrapper);
//        System.out.println(map);
//    }
//
//    /*按照条件查询返回List<Map>,返回多个值*/
//    @Test
//    public void t16() {
//        QueryWrapper userQueryWrapper = new QueryWrapper();
//        userQueryWrapper.like("username","zqw2");
//        List<Map<String, Object>> list = userService.listMaps(userQueryWrapper);
//        if (list != null) list.stream().forEach(System.out::println);
//    }
//
//    /*按照条件查询返回Object*/
//    @Test
//    public void t17() {
//        QueryWrapper userQueryWrapper = new QueryWrapper();
//        userQueryWrapper.like("username","zqw2");
//        Object obj = userService.getObj(userQueryWrapper);
//        System.out.println(obj);
//    }
//
//    /*按照条件查询一个值*/
//    @Test
//    public void t18() {
//        QueryWrapper userQueryWrapper = new QueryWrapper();
//        userQueryWrapper.like("username","zqw2");
//        User one = userService.getOne(userQueryWrapper);
//        System.out.println(one);
//    }
//
//    /*按照条件查询一个值,如果这个条件查到的不是一个值，而是多个，那么抛出异常*/
//    @Test
//    public void t19() {
//        QueryWrapper userQueryWrapper = new QueryWrapper();
//        userQueryWrapper.like("username","zqw2");
//        User one = userService.getOne(userQueryWrapper, true);
//        System.out.println(one);
//    }
//
//    /*按照多个ID查询*/
//    @Test
//    public void t20() {
//        Collection<User> users = userService.listByIds(Arrays.asList(1, 2, 3, 4, 5));
//        if(users != null) users.forEach(System.out::println);
//    }
//
//
//    /*按照一个Map多个条件与查询*/
//    @Test
//    public void t21() {
//        Map map = new HashMap();
//        map.put("username", "zqw27");
//        map.put("id",71);
//        Collection collection = userService.listByMap(map);
//        if(collection != null) collection.forEach(System.out::println);
//    }
//
//    /*测试一个动态SQL*/
//    @Test
//    public void t27() {
//        String sql = "SELECT * FROM USER";
//        List<Map> collection = dynamicMapper.dynamicSelect(sql);
//        if(collection != null) collection.forEach(System.out::println);
//    }
//
//    /*乐观锁*/
//    @Test
//    public void t28() {
//        User user = userService.getById(1);
//        user.setPassword("123456");
//        userService.updateById(user);
//        System.out.println(user);
//    }
//}
