package com.iscas.biz.domain.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author zhuquanwen
 */
@Data
@Accessors(chain = true)
public class Role {
    @TableId(type = IdType.AUTO)
    private Integer roleId;

    private String roleName;

    private Date roleCreateTime;

    private Date roleUpdateTime;

}