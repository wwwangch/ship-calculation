package com.iscas.biz.mapper.common;

import com.iscas.biz.domain.common.Menu;
import com.iscas.biz.mp.enhancer.mapper.DynamicMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author zhuquanwen
 */
@SuppressWarnings({"MybatisXMapperMethodInspection", "AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc", "rawtypes"})
@Repository
public interface MenuMapper extends DynamicMapper<Menu> {

    List<Map> selectMenuOpration();

    List<Map> selectMenuRole();
}