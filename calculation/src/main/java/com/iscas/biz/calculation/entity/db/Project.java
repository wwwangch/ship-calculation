package com.iscas.biz.calculation.entity.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.iscas.biz.calculation.enums.CalculationSpecification;
import lombok.Data;

import java.util.Date;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/11 16:05
 */
@Data
public class Project {
    @TableId(type = IdType.AUTO)
    private Integer projectId;

    private String projectName;
    /**
     * 计算规范
     */
    private CalculationSpecification calculationSpecification;

    private String remark;

    private Date createTime;

    private Date updateTime;
}
