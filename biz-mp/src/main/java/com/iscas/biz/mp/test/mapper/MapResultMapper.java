package com.iscas.biz.mp.test.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author zhuquanwen
 * @date 2018/7/16 14:44
 **/
@SuppressWarnings("rawtypes")
@Repository
@Mapper
public interface MapResultMapper {
    /**
     * 查询返回到一个List<MapResponseData>中
     *
     * @return List<Map>
     */
    @Select("SELECT * FROM user ")
    List<Map> select();

    /**
     * 查询单个数据返回到Map
     *
     * @return Map
     */
    @SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
    @Select("SELECT * FROM parent where id = #{id,jdbcType=INTEGER} ")
    Map selectById(Integer id);
}
