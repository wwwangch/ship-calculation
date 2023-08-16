package com.iscas.biz.calculation.entity.db.sigma;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.iscas.biz.calculation.enums.CheckType;
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

//    //许用应力
//    private Double allowStress;
    //合成许用应力
    private Double combineAllowStress;
    //合成中拱支座上纤维
    private Double combineZhonggongZhizuoShang;
    //合成中拱支座下纤维
    private Double combineZhonggongZhizuoXia;

    //合成中拱跨中上纤维
    private Double combineZhonggongKuazhongShang;
    //合成中拱跨中下纤维
    private Double combineZhonggongKuazhongXia;

    //合成中垂支座上纤维
    private Double combineZhongchuiZhizuoShang;
    //合成中垂支座下纤维
    private Double combineZhongchuiZhizuoXia;

    //合成中垂跨中上纤维
    private Double combineZhongchuiKuazhongShang;
    //合成中垂跨中下纤维
    private Double combineZhongchuiKuazhongXia;

    //工况类型
    private CheckType checkType;
}
