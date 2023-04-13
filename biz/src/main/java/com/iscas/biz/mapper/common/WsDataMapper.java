package com.iscas.biz.mapper.common;

import com.iscas.biz.domain.common.WsData;
import com.iscas.biz.mp.enhancer.mapper.DynamicMapper;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author zhuquanwen
 */
@Repository
public interface WsDataMapper extends DynamicMapper<WsData> {
    @SuppressWarnings("UnusedReturnValue")
    @Delete("delete from ws_data where create_time < #{time,jdbcType=TIMESTAMP}")
    int deleteByTime(Date time);
}