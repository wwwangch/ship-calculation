package com.iscas.biz.domain.common;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zhuquanwen
 */
@TableName("org_user")
@Data
public class OrgUserKey {
    private Integer orgId;

    private Integer userId;
}