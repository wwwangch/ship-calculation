package com.iscas.biz.calculation.entity.db;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.iscas.common.web.tools.json.JsonUtils;
import lombok.Data;

import java.util.Date;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/12 15:49
 * T型材
 */
@Data
@TableName("t_profile")
@ExcelIgnoreUnannotated
public class TProfile {
    @TableId(type = IdType.AUTO)
    private Integer profileId;
    @ExcelProperty(index = 0)
    private String model;   //型号
    @ExcelProperty(index = 1)
    private Double keelHeight;    // 腹板高度(mm)
    @ExcelProperty(index = 2)
    private Double keelThickness; // 腹板厚度(mm)
    @ExcelProperty(index = 3)
    private Double deckWidth;     // 面板宽度(mm)
    @ExcelProperty(index = 4)
    private Double deckThickness; // 面板厚度(mm)
    @ExcelProperty(index = 5)
    private Double sectionalArea; // 剖面积(cm²)
    @ExcelProperty(index = 6)
    private Double momentOfInertia; // 惯性矩(cm⁴)
    @ExcelProperty(index = 7)
    private Double centroidPosition; // 形心位置(cm)
    private Date createTime; //创建时间
    private Date updateTime; //修改时间

    public static void main(String[] args) {
        TProfile tProfile=new TProfile();
        tProfile.setModel("xx");
        tProfile.setKeelHeight(12D);
        tProfile.setKeelThickness(12D);
        tProfile.setDeckWidth(12D);
        tProfile.setDeckThickness(12D);
        tProfile.setSectionalArea(12D);
        tProfile.setMomentOfInertia(12D);
        tProfile.setCentroidPosition(12D);
        System.out.println(JsonUtils.toJson(tProfile));
    }
}
