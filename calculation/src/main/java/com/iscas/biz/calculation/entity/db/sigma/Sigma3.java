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
@TableName(value = "sigma3", autoResultMap = true)
public class Sigma3 {
    @TableId(type = IdType.AUTO)
    private Integer sigma3Id;
    private Integer projectId;
    private Integer sectionId;

    //中拱支座上纤维
    private Double zhonggongZhizuoShang;
    //中拱支座下纤维
    private Double zhonggongZhizuoXia;

    //中拱跨中上纤维
    private Double zhonggongKuazhongShang;
    //中拱跨中下纤维
    private Double zhonggongKuazhongXia;

    //中垂支座上纤维
    private Double zhongchuiZhizuoShang;
    //中垂支座下纤维
    private Double zhongchuiZhizuoXia;

    //中垂跨中上纤维
    private Double zhongchuiKuazhongShang;
    //中垂跨中下纤维
    private Double zhongchuiKuazhongXia;

}
