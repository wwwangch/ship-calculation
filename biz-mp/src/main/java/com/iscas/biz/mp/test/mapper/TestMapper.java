package com.iscas.biz.mp.test.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.iscas.biz.mp.enhancer.mapper.DynamicMapper;
import com.iscas.biz.mp.test.model.Test;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/3/26 14:08
 * @since jdk1.8
 */
@SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
@Repository
@Mapper
public interface TestMapper extends DynamicMapper<Test> {

    IPage<Test> testSelectPage(IPage<Test> page);
}
