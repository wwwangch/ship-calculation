package com.iscas.biz.mp.test.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/3/20 17:03
 */
@Data
public class TestInsert {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer age;

    public TestInsert() {
    }

    public TestInsert(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
