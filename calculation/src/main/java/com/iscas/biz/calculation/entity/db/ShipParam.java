package com.iscas.biz.calculation.entity.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.iscas.biz.calculation.enums.NavigationArea;
import com.iscas.biz.calculation.enums.ShipType;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/11 15:40
 * 船舶参数
 */
@Data
public class ShipParam {
    @TableId(type = IdType.AUTO)
    private Integer paramId;

    private Integer projectId;

    /**
     * 船类型 ZQJ HM
     */
    private ShipType shipType;

    /**
     * 设计水线长(m)
     */
    private Double waterlineLength;

    /**
     * 型宽(m)
     */
    private Double mouldedBreadth;

    /**
     * 型深(m)
     */
    private Double mouldedDepth;

    /**
     * 设计吃水(m)
     */
    private Double designedDraft;

    /**
     * 站数
     */
    private Integer station;

    /**
     * 航区 有限和无限
     */
    private NavigationArea navigationArea;

    /**
     * 极限波浪工况排水量
     */
    private Double extremeDisplacement;

    /**
     * 极限波浪重心纵向位置
     */
    private String extremePortraitGravity;

    /**
     * 巡航工况排水量
     */
    private Double cruisingDisplacement;

    /**
     * 巡航纵向重心纵向位置
     */
    private String cruisingPortraitGravity;

    @TableField(exist = false)
    private MultipartFile paramFile;

    /**
     * 如果是上传文件的需要保存文件路径
     */
    private String paramFilePath;

    private Date createTime;

    private Date updateTime;
}
