package com.iscas.biz.calculation.entity.db.sigma;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author yichuan@iscas.ac.cn
 * @Date 2023/5/31 17:34
 */
@Data
@TableName(value = "shearing_stress", autoResultMap = true)
public class ShearingStress {
    @TableId(type = IdType.AUTO)
    private Integer shearSressId;
    private Integer projectId;
    private Integer sectionId;

    //中垂剖面最大剪力值τ
    private Double zhongchuiMax;
    //中拱剖面最大剪力值τ
    private Double zhonggongMax;
    //许用剪应力[τ]
    private Double shearingStress;
}
