package com.iscas.biz.domain.common;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhuquanwen
 */
@Data
@Accessors(chain = true)
@Schema(title = "组织结构")
@TableName(value = "org", excludeProperty = {"roleNames", "roleIds"})
public class Org {

    @Schema(title="ID")
    @TableId(type = IdType.AUTO)
    private Integer orgId;

    @Schema(title="名称")
    @NotEmpty(message = "{org.name.empty.constraint.message}")
    @Size(min = 2, max = 50, message = "{org.name.size.constraint.message}")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String orgName;

    @Schema(title="父ID")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Integer orgPid;

    @Schema(title="创建时间")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Date orgCreateTime;

    @Schema(title="修改时间")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Date orgUpdateTime;

    @Schema(title="描述")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String orgDesc;

    @Schema(title="角色(显示)")
    private String roleNames;

    @Schema(title="角色ID(不显示)")
    private List<Integer> roleIds = new ArrayList<>();

}