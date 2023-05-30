package com.iscas.biz.calculation.entity.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author zhaotianci@iscas.ac.cn
 * @date 2023/5/28 0:20
 */
@Data
public class Dist {
    @TableId(type = IdType.AUTO)
    private Integer distId;

    private Integer projectId;

    /**
     * 中拱极限弯矩
     */
    private Double extremeH;

    /**
     * 中垂极限弯矩
     */
    private Double extremeS;
}
