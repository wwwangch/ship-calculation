package com.iscas.base.biz.model.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 菜单
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/16 18:41
 * @since jdk1.8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode
public class Menu implements Serializable {
    private String key;
    private String name;

}
