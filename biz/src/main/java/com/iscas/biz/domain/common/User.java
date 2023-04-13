package com.iscas.biz.domain.common;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@TableName("user_info")
public class User {
   @TableId(type = IdType.AUTO)
   private Integer userId;

   @TableField(updateStrategy = FieldStrategy.IGNORED)
   private String userName;

   @TableField(updateStrategy = FieldStrategy.IGNORED)
   private String userRealName;

   @TableField(updateStrategy = FieldStrategy.IGNORED)
   private String userPwd;

   @TableField(updateStrategy = FieldStrategy.IGNORED)
   private String userTel;

   @TableField(updateStrategy = FieldStrategy.IGNORED)
   private String userEmail;

   @TableField(updateStrategy = FieldStrategy.IGNORED)
   private Integer userStatus;

   @TableField(updateStrategy = FieldStrategy.IGNORED)
   private Date userCreateTime;

   @TableField(updateStrategy = FieldStrategy.IGNORED)
   private Date userUpdateTime;

   @TableField(exist = false)
   private Integer count;
}