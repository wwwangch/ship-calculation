package com.iscas.base.biz.model.auth;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;

/**
 * @author zhuquanwen
 */
@Getter
@Setter
public class User implements Principal, Serializable {

    private String username;

    private String password;

    private String role;

    private List<Url> urls;

    @Override
    public String getName() {
        return username;
    }
}