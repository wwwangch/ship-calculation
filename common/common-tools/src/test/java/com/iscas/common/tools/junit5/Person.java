package com.iscas.common.tools.junit5;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * //测试使用model
 *
 * @author yuemingyang
 * @version 1.0
 * @date 2021/2/18 14:02
 * @since jdk1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String firstName;
    private String lastName;
    private String code;
    private Date time;
}
