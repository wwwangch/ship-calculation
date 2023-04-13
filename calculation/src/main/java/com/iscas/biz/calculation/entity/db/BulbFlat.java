package com.iscas.biz.calculation.entity.db;

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
public class BulbFlat {
    @TableId(type = IdType.AUTO)
    private Integer profileId;
    private String model;   //型号
    private Double height;         // 高度(mm)
    private Double width;          // 宽度(mm)
    private Double thickness;      // 厚度(mm)
    private Double sectionalArea;  // 剖面积(cm²)
    private Double momentOfInertia;// 惯性矩(cm⁴)
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
