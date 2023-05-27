package com.iscas.biz.calculation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iscas.biz.calculation.entity.db.Bulkhead;
import com.iscas.biz.calculation.entity.db.BulkheadCompartment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/20 14:08
 * 剖面数据库交互对象
 */
@Mapper
public interface BulkheadCompartmentMapper extends BaseMapper<BulkheadCompartment> {
}
