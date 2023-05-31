package com.iscas.biz.calculation.entity.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/20 13:57
 * 剖面-用于总纵强度计算
 */
@Data
public class Section {
    @TableId(type = IdType.AUTO)
    private Integer sectionId;

    private Integer projectId;

    /**
     * 原先为：校核剖面位置X xCoordinate
     * 修改为：剖面位置x-横梁间距  girderDistance
     */
    private Double girderDistance;

    /**
     * 剖面文件名称
     */
    private String sectionFileName;

    /**
     * 剖面文件路径
     */
    private String sectionFilePath;
    /**
     *
     * 原先为：剖面构件跨距a  componentSpan
     * 修改为：构件跨距-龙骨跨距 每个的跨距
     */
    private Double kuaChang;

    private Date createTime;

    private Date updateTime;

    /**
     * 肋位号
     */
    private Double ribNumber;

    /**
     * 纵骨间距
     */
    private Double frDistance;
    /**
     * 纵骨规格 每个的规格
     */
    private Double frGuige;
    /**
     *板格厚度 每个板材的厚度
     */
    private Double plateThick;
    /**
     * 设备重量
     */
    private Double deviceWeight;
    /**
     * 板格宽度
     */
    private Double girderWidth;
    /**
     * 材料类型
     */
    private String materialType;



}
