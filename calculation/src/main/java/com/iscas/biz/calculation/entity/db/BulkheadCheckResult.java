package com.iscas.biz.calculation.entity.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.iscas.biz.calculation.enums.CheckType;
import lombok.Data;

import java.util.List;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/6/3 15:52
 */
@Data
@TableName(value = "bulkhead_check_result", autoResultMap = true)
public class BulkheadCheckResult {

    //舱壁校核结果id
    @TableId(type = IdType.AUTO)
    private Integer bulkheadResultId;

    //舱壁Id
    private Integer bulkheadId;

    //项目id
    private Integer projectId;

    //工况类型
    private CheckType checkType;

    //层间名称
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> strdeckdistrict;

    //均布载荷
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> disload;
    //lgv
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> lgvList;

    //u输出
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> uList;
    //chi1 输出
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> chi1List;
    //chi2 输出
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> chi2List;
    //悬链应力
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> stressXlList;
    //跨中应力
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> stressKuozhong;
    //支座应力
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> stressZhizuo;
    //许用剪力
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> shearAllow;
}
