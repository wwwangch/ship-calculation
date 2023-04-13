package com.iscas.biz.mapper.common;

import com.iscas.biz.domain.common.OrgUserKey;
import com.iscas.biz.mp.enhancer.mapper.DynamicMapper;
import org.springframework.stereotype.Repository;

/**
 * @author zhuquanwen
 */
@Repository
public interface OrgUserMapper extends DynamicMapper<OrgUserKey> {
    
}