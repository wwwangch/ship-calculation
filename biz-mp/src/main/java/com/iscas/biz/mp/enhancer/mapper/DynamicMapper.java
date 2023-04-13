package com.iscas.biz.mp.enhancer.mapper;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.ResultSetType;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 执行SQL语句的Mapper
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/9/27 10:23
 * @since jdk1.8
 */
@SuppressWarnings({"rawtypes", "unused"})
@Repository
@ConditionalOnMybatis
public interface DynamicMapper<T> extends BaseMapper<T> {

    /**
     * 扩展Mybatis-Plus的方法
     * truncate
     */
    void truncate();

    /**
     * 扩展Mybatis-Plus的方法
     * fetchByStream
     *
     * @param wrapper wrapper
     * @param handler 处理
     * @date 2022/4/22
     * @since jdk11
     */
    void fetchByStream(@Param(Constants.WRAPPER) Wrapper<T> wrapper, ResultHandler<T> handler);


    /**
     * 批量新增数据,自选字段 insert
     * 不会分批插入，需要分批请调用方法insertBatch或者 insertBatchSomeColumn(List<T> entityList, int size)
     * 此填充不会填充 FieldFill.UPDATE 的字段。
     * 注意数据库默认更新的字段也需要手工设置
     *
     * @param entityList 数据
     * @return 插入条数
     */
    int insertBatchSomeColumn(List<T> entityList);

    /**
     * 分批插入
     *
     * @param entityList 原实体对象
     * @param size       分批大小
     * @return 总插入记录
     */
    @Transactional(rollbackFor = Exception.class)
    default int insertBatchSomeColumn(List<T> entityList, int size) {
        if (CollUtil.isEmpty(entityList)) {
            return 0;
        }
        List<List<T>> split = CollUtil.split(entityList, size);
        return split.stream().mapToInt(this::insertBatchSomeColumn).sum();
    }


    /**
     * 通过SQL查询一个值
     *
     * @param sql SQL
     * @return java.util.Map
     * @date 2022/4/22
     * @since jdk11
     */
    @Select("${sql}")
    Map selectOneBySql(@Param("sql") String sql);

    /**
     * 查询，结果返回集合
     *
     * @param sql SQL
     * @return java.util.List<java.util.Map>
     * @date 2022/4/22
     * @since jdk11
     */
    @Select("${sql}")
    List<Map> selectBySql(@Param("sql") String sql);

    /**
     * 按照SQL插入
     *
     * @param sql SQL语句
     * @date 2022/4/22
     * @since jdk11
     */
    @Insert("${sql}")
    void insertBySql(@Param("sql") String sql);

    /**
     * 按照SQL修改
     *
     * @param sql SQL语句
     * @date 2022/4/22
     * @since jdk11
     */
    @Update("${sql}")
    void updateBySql(@Param("sql") String sql);

    /**
     * 按照SQL删除
     *
     * @param sql SQL
     * @date 2022/4/22
     * @since jdk11
     */
    @Delete("${sql}")
    void deleteBySql(@Param("sql") String sql);

    /**
     * 按照SQL查询大数据量数据
     *
     * @param sql     SQL语句
     * @param handler handler
     * @date 2022/4/22
     * @since jdk11
     */
    @Select("${sql}")
    @Options(resultSetType = ResultSetType.FORWARD_ONLY, fetchSize = 1000)
    @ResultType(Map.class)
    void selectLargeDataBySql(@Param("sql") String sql, ResultHandler<Map> handler);

    /**
     * 批量执行SQL
     *
     * @param sqls SQL集合
     * @date 2022/4/22
     * @since jdk11
     */
    @Update("<script><foreach close=\"\" collection=\"sqls\" index=\"index\" item=\"item\" open=\"\" separator=\";\">  " +
            "     ${item}       " +
            "        </foreach></script>  ")
    void batchBySql(@Param("sqls") List<String> sqls);

}
