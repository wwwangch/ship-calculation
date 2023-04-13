package com.iscas.base.biz.model.auth;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * URL
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/16 18:41
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class Url implements Serializable {
    private String key;
    private String name;
}
