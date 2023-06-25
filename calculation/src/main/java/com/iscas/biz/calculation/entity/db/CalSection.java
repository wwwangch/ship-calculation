package com.iscas.biz.calculation.entity.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import com.iscas.biz.calculation.grpc.BulbFlat;
import com.iscas.biz.calculation.grpc.TProfile;
import java.util.List;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/5/13 15:40
 */
@Data
@TableName(value = "cal_section", autoResultMap = true)
public class CalSection {

    @TableId(type = IdType.AUTO)
    private Integer calSectionId;

    private Integer projectId;
    private Integer sectionId;

    /**
     * 载重文件路径
     */
    private String profileFilePathOld;

    /**
     * 载重文件名称
     */
    private String profileFileName;

    /**
     * 肋位号
     */
    private Double ribNumber;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<BulbFlat> bulbFlats;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<TProfile> tProfiles;
    private boolean isHalfProfile;

    //初始静矩
    private Double firstMoment0;

    //惯性矩
    private Double interia0;

    //初始中和轴
    private Double zaxis0;

    //剖面面积
    private Double area ;
    //上甲板模数
    private Double moduleUppper ;
    //底部模数
    private Double moduleLower ;

    //解析后的剖面文件路径
    private String profileFilePath ;

//    //中拱时的中和轴
//    private Double zaxisH;
//
//    //中拱时静矩
//    private Double firstMomH;
//
//    //中拱时惯性矩
//    private Double interiaH;
//
//    //中垂时的中和轴
//    private Double zaxisS;
//
//    //中垂时静矩
//    private Double firstMomS;
//
//    //中垂时惯性矩
//    private Double interiaS;

}
