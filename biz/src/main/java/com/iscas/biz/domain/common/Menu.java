package com.iscas.biz.domain.common;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author zhuquanwen
 */
@Schema(title = "菜单")
@Data
@Accessors(chain = true)
@TableName(value = "menu", excludeProperty = {"roleIds", "roleNames", "oprationIds", "oprationNames"})
public class Menu implements Serializable {

    @Schema(title = "ID")
    @TableId(type = IdType.AUTO)
    private Integer menuId;

    @Schema(title = "上级菜单")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Integer menuPid;

    @Schema(title = "菜单路径")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String menuPage;

    @Schema(title = "菜单创建时间")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Date menuCreateTime;

    @Schema(title = "菜单修改时间")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Date menuUpdateTime;

    @Schema(title = "菜单名称")
    @NotEmpty(message = "{menu.name.empty.constraint.message}")
    @Size(min = 2, max = 50, message = "{menu.name.size.constraint.message}")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String menuName;

    @Schema(title = "角色ID（多选）不显示")
    private List<Integer> roleIds = new ArrayList<>();

    @Schema(title = "角色名称，显示")
    private String roleNames;

    @Schema(title = "权限标识ID,不显示")
    private List<Integer> oprationIds = new ArrayList<>();

    @Schema(title = "权限标识名称，显示")
    private String oprationNames;

}