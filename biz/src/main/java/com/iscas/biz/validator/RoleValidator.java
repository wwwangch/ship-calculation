package com.iscas.biz.validator;

import com.iscas.biz.validator.anno.RoleConstraint;
import org.apache.commons.lang3.StringUtils;

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
@SuppressWarnings("AlibabaUndefineMagicConstant")
public class RoleValidator implements ConstraintValidator<RoleConstraint, Map<String, Object>> {
    @Override
    public boolean isValid(Map<String, Object> user, ConstraintValidatorContext context) {
        if (!user.containsKey("role_name")) {
            return false;
        }
        String userName = (String) user.get("role_name");
        return !StringUtils.isBlank(userName) && userName.length() >= 2 && userName.length() <= 50;
    }
}
