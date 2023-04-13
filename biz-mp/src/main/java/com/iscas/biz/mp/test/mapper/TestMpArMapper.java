package com.iscas.biz.mp.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iscas.biz.mp.test.model.TestMpAr;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/4/7 20:56
 * @since jdk1.8
 */
@Repository
@Mapper
public interface TestMpArMapper extends BaseMapper<TestMpAr> {
}
