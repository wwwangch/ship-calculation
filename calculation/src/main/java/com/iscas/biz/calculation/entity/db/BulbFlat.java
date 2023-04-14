package com.iscas.biz.calculation.entity.db;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.iscas.common.web.tools.json.JsonUtils;
import lombok.Data;

import java.util.Date;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/12 16:22
 * 球扁钢
 */
@Data
@ExcelIgnoreUnannotated
public class BulbFlat {
    @TableId(type = IdType.AUTO)
    private Integer profileId;
    @ExcelProperty(index = 0)
    private String model;   //型号
    @ExcelProperty(index = 1)
    private Double height;         // 高度(mm)
    @ExcelProperty(index = 2)
    private Double width;          // 宽度(mm)
    @ExcelProperty(index = 3)
    private Double thickness;      // 厚度(mm)
    @ExcelProperty(index = 4)
    private Double sectionalArea;  // 剖面积(cm²)
    @ExcelProperty(index = 5)
    private Double momentOfInertia;// 惯性矩(cm⁴)
    @ExcelProperty(index = 6)
    private Double centroidPosition; // 形心位置(cm)
    private Date createTime; //创建时间
    private Date updateTime; //修改时间

    public static void main(String[] args) {
        BulbFlat bulbFlat=new BulbFlat();
        bulbFlat.setModel("xx");
        bulbFlat.setHeight(12D);
        bulbFlat.setWidth(12D);
        bulbFlat.setThickness(12D);
        bulbFlat.setSectionalArea(12D);
        bulbFlat.setMomentOfInertia(12D);
        bulbFlat.setCentroidPosition(12D);
        System.out.println(JsonUtils.toJson(bulbFlat));
    }
}
