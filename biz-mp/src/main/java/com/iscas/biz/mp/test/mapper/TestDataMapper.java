package com.iscas.biz.mp.test.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iscas.biz.mp.test.model.TestData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author admin
 */
@SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
@Mapper
@Repository
public interface TestDataMapper extends BaseMapper<TestData> {

    @Select("SELECT * FROM TEST_DATA")
    List<TestData> toSelectAll(RowBounds rowBounds);

}