package com.iscas.biz.mp.test.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.mp.config.db.DynamicTableNameHolder;
import com.iscas.biz.mp.enhancer.mapper.DynamicMapper;
import com.iscas.biz.mp.test.mapper.TestMapper;
import com.iscas.biz.mp.test.model.Test;
import com.iscas.biz.mp.test.service.impl.TestService;
import com.iscas.templet.common.BaseController;
import com.iscas.templet.common.ResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/3/26 14:09
 * @since jdk1.8
 */
@SuppressWarnings({"rawtypes", "unused", "unchecked"})
@RestController
@RequestMapping("/test/mybatis/plus")
@ConditionalOnMybatis
@RequiredArgsConstructor
public class TestController extends BaseController {

    private final TestMapper testMapper;

    private final TestService testService;

    private final DynamicMapper dynamicMapper;


    /**
     * 测试自定义表名
     * */
    @GetMapping("/dynamic-tablename")
    public ResponseEntity dynamicTableName() {
        ResponseEntity response = getResponse();
        try {
            DynamicTableNameHolder.set("test_copy1");
            List<Test> tests = testMapper.selectList(null);
            response.setValue(tests);
            List<Test> tests1 = testMapper.selectList(null);
        } finally {
            DynamicTableNameHolder.remove();
        }
        List<Test> tests = testMapper.selectList(null);
        return response;
    }

    /**
     * 测试分页查询
     */
    @GetMapping("/page")
    public ResponseEntity testPage() {
        ResponseEntity response = getResponse();
        IPage<Test> page = new Page<>();
        page.setSize(2);
        page.setCurrent(1);
        IPage<Test> testPage = testMapper.selectPage(page, null);
        response.setValue(testPage.getRecords());
        return response;
    }

    /**
     * 测试自定义函数truncate
     */
    @GetMapping("/truncate")
    public ResponseEntity testTruncate() {
        testMapper.truncate();
        return getResponse();
    }

    /**
     * 测试自定义函数流式拉取数据
     */
    @GetMapping("/fetchByStream")
    public ResponseEntity fetchByStream() {
        List<Test> result = new ArrayList<>();
        testMapper.fetchByStream(new QueryWrapper<Test>().eq("name", "222"), resultContext -> result.add(resultContext.getResultObject()));
        return getResponse().setValue(result);
    }

    /**
     * 测试自定义方法分页
     */
    @GetMapping("/custom/page")
    public ResponseEntity testCustomMethodPage() {
        ResponseEntity response = getResponse();
        IPage<Test> page = new Page<>();
        page.setSize(2);
        page.setCurrent(1);
        IPage<Test> testPage = testMapper.testSelectPage(page);
        response.setValue(testPage.getRecords());
        return response;
    }

    /**
     * 测试queryWrapper
     */
    @GetMapping("/queryWrapper")
    public ResponseEntity testQueryWrapper() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("id", "name");
        queryWrapper.gt("age", "2");
        List list = testMapper.selectList(queryWrapper);
        return getResponse().setValue(list);
    }

    /**
     * 测试lambdaWrapper
     */
    @GetMapping("/queryLambdaWrapper")
    public ResponseEntity testLambdaWrapper() {
        LambdaQueryWrapper<Test> lambda = new QueryWrapper<Test>().lambda();
        lambda.select(Test::getId, Test::getName, Test::getAge)
                .eq(Test::getName, "222");
        List<Test> tests = testMapper.selectList(lambda);
        return getResponse().setValue(tests);
    }

    /**
     * 测试lambdaWrapper2
     */
    @GetMapping("/queryLambdaWrapper2")
    public ResponseEntity testLambdaWrapper2() {
        LambdaQueryWrapper<Test> lambda = new QueryWrapper<Test>().lambda();
        lambda.select(Test::getId, Test::getName, Test::getAge)
                .eq(Test::getName, "222")
                .or(l -> l.gt(Test::getAge, 2));
        List<Test> tests = testMapper.selectList(lambda);
        return getResponse().setValue(tests);
    }

    /**
     * 测试lambdaWrapper3
     */
    @GetMapping("/queryLambdaWrapper3")
    public ResponseEntity testLambdaWrapper3() {
        LambdaQueryWrapper<Test> lambda = new QueryWrapper<Test>().lambda();
        lambda.select(Test::getId, Test::getName, Test::getAge)
                .gt(Test::getAge, 12)
                .and(l -> l.lt(Test::getAge, 36));
        List<Test> tests = testMapper.selectList(lambda);
        return getResponse().setValue(tests);
    }


    //====================测试service里的方法=============================

    /**
     * 测试使用updateWrapper修改
     * */
    @GetMapping("/updateWrapper")
    public ResponseEntity test1() {
        UpdateWrapper<Test> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("age", 55);
        updateWrapper.eq("id", 1);
        testService.update(updateWrapper);
        return getResponse();
    }

    /**
     * 测试链式查询
     * */
    @GetMapping("/chain")
    public ResponseEntity test2() {
        Test test = testService.lambdaQuery()
                .eq(Test::getId, 1).one();
        return getResponse().setValue(test);
    }

    /**
     * 测试逻辑删除
     * */
    @GetMapping("/logic")
    public ResponseEntity testLogic() {
        testService.removeById(5);
        return getResponse();
    }

    /**
     * 测试属性自动填充
     * */
    @GetMapping("/fill1")
    public ResponseEntity testFill1() {
        Test test = new Test();
        test.setName("张三");
        test.setAge(12);
        testService.save(test);
        return getResponse();
    }

    /**
     * 测试属性自动填充
     * */
    @GetMapping("/fill2")
    public ResponseEntity testFill2() {
        List<Test> list = testService.list(new QueryWrapper<Test>().lambda().eq(Test::getName, "张三"));
        list.forEach(test -> test.setUpdateTime(null));
        testService.updateBatchById(list);
        return getResponse();
    }

}
