package com.iscas.biz.calculation.entity.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/20 13:57
 * 横舱壁-用于局部强度计算
 */
@Data
public class Bulkhead {
    @TableId(type = IdType.AUTO)
    private Integer bulkheadId;

    private Integer projectId;

    /**
     * 剖面文件名称
     */
    private String sectionFileName;

    /**
     * 各层甲板高度文件名称
     */
    private String bulkheadFileName;
    /**
     * 是否防撞舱壁
     */
    private String collisionBulkhead;

    private Date createTime;

    private Date updateTime;
}
