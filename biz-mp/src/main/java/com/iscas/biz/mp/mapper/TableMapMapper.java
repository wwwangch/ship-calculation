package com.iscas.biz.mp.mapper;

import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.mp.model.DynamicSql;
import org.apache.ibatis.annotations.Insert;
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
@ConditionalOnMybatis
public interface TableMapMapper {
    /**
     * 动态查询
     *
     * @param dynamicSql 动态sql
     * @return java.util.List<java.util.Map>
     * @date 2022/4/22
     * @since jdk11
     */
    @Select("${sql}")
    List<Map> dynamicSelect(DynamicSql dynamicSql);

    /**
     * 动态插入
     *
     * @param dynamicSql 动态SQL
     * @return int
     * @date 2022/4/22
     * @since jdk11
     */
    @Insert("${sql}")
    int dynamicInsert(DynamicSql dynamicSql);
}
