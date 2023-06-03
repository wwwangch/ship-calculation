package com.iscas.biz.calculation.entity.db.sigma;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author yichuan@iscas.ac.cn
 * @Date 2023/5/31 17:33
 */
@Data
@TableName(value = "sigma4", autoResultMap = true)
public class Sigma4 {
    @TableId(type = IdType.AUTO)
    private Integer sigma4Id;
    private Integer projectId;
    private Integer sectionId;

    //中拱支座
    private Double zhonggongZhizuo;

    //中拱跨中
    private Double zhonggongKuazhong;

    //中垂支座
    private Double zhongchuiZhizuo;

    //中垂跨中
    private Double zhongchuiKuazhong;
    //许用应力
    private Double allowStress;
}
