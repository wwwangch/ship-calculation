package com.iscas.biz.calculation.entity.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.iscas.biz.calculation.enums.CalculationSpecification;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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

    /**
     * 计算规范
     */
    private CalculationSpecification calculationSpecification;

    private String remark;

    private Date createTime;

    private Date updateTime;
}
