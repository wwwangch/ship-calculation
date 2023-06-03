package com.iscas.biz.calculation.entity.db.sigma;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author yichuan@iscas.ac.cn
 * @Date 2023/5/31 17:33
 */
@Data
@TableName(value = "sigma1",autoResultMap = true)
public class Sigma1 {
    @TableId(type = IdType.AUTO)

    private Integer sigma1Id ;

    private Integer projectId ;

    private Integer sectionId;

    //中拱龙骨上纤维
    private Double sigma1HUp ;
    //中拱龙骨下纤维
    private Double sigma1Down ;
    //中垂龙骨上纤维
    private Double sigma1SUp ;
    //中垂龙骨上纤维
    private Double sigma1SDown ;
    //许用应力
    private Double allowStress;
}
