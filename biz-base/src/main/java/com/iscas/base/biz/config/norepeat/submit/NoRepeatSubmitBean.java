package com.iscas.base.biz.config.norepeat.submit;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/5/27 22:03
 * @since jdk1.8
 */
@Data
@AllArgsConstructor
public class NoRepeatSubmitBean {
    private NoRepeatSubmitLockType lockType;
}
