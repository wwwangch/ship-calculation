package com.iscas.biz.calculation.entity.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.iscas.biz.calculation.enums.CheckType;
import lombok.Data;

/**
 * @author zhaotianci@iscas.ac.cn
 * @date 2023/5/28 0:20
 */
@Data
public class Dist {
    @TableId(type = IdType.AUTO)
    private Integer distId;

    /**
     * 项目ID
     */
    private Integer projectId;

    /**
     * 剖面ID
     */
    private Integer sectionId;

    /**
     * 中拱极限弯矩
     */
    private Double extremeH;

    /**
     * 中垂极限弯矩
     */
    private Double extremeS;

    //中拱过载系数1
    private Double overloadH1 ;
    //中拱过载系数2
    private Double overloadH2 ;
    //中垂过载系数1
    private Double overloadS1 ;
    //中垂过载系数2
    private Double overloadS2 ;

    //应力图片路径
    private String stressdisPath;

    //工况类型
    private CheckType checkType;
}
