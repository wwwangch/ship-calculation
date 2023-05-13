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
     * 校核剖面位置X
     */
    private Double xCoordinate;

    /**
     * 剖面文件名称
     */
    private String sectionFileName;

    /**
     * 剖面文件路径
     */
    private String sectionFilePath;
    /**
     * 剖面构件跨距a
     */
    private Double componentSpan;

    private Date createTime;

    private Date updateTime;
}
