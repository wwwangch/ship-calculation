package com.iscas.biz.domain.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author zhuquanwen
 */
@Data
@Accessors(chain = true)
@TableName("resources")
public class Resource {
    @TableId(type = IdType.AUTO)
    private Integer resourceId;

    private String resourceUrl;

    private Date resourceCreateTime;

    private Date resourceUpdateTime;

    private String resourceDesc;

}