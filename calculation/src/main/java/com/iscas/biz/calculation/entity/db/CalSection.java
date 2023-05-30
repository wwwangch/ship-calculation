package com.iscas.biz.calculation.entity.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.util.List;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/5/13 15:40
 */
@Data
@TableName(value = "cal_section", autoResultMap = true)
public class CalSection {

    @TableId(type = IdType.AUTO)
    private Integer calSectionId;

    private Integer projectId;
    private Integer sectionId;

    /**
     * 载重文件路径
     */
    private String profileFilePath;

    /**
     * 载重文件名称
     */
    private String profileFileName;


    //初始静矩
    private Double firstMoment0;

    //惯性矩
    private Double interia0;

    //中拱时的中和轴
    private Double zaxisH;

    //中拱时静矩
    private Double firstMomH;

    //中拱时惯性矩
    private Double interiaH;

    //中垂时的中和轴
    private Double zaxisS;

    //中垂时静矩
    private Double firstMomS;

    //中垂时惯性矩
    private Double interiaS;

}
