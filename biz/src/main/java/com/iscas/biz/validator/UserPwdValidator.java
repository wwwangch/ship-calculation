package com.iscas.biz.validator;

import com.iscas.biz.validator.anno.UserPwdConstraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Map;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/12/6 14:31
 * @since jdk1.8
 */
public class UserPwdValidator implements ConstraintValidator<UserPwdConstraint, Map<String, Object>> {
    @Override
    public boolean isValid(Map<String, Object> loginInfo, ConstraintValidatorContext context) {
        return loginInfo.containsKey("oldPwd") && loginInfo.containsKey("newPwd");
    }
}
