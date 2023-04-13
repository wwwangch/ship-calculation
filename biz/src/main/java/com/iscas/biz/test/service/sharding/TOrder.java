package com.iscas.biz.test.service.sharding;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/5/28 21:11
 * @since jdk1.8
 */
@Data
public class TOrder {
    @TableId(type= IdType.ASSIGN_ID)
    private Integer orderId;
    private Integer userId;
    private String orderName;
    private String orderDesc;
}
