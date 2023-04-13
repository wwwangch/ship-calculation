package com.iscas.common.tools.junit5;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * //TODO
 *
 * @author yuemingyang
 * @version 1.0
 * @date 2021/2/18 17:26
 * @since jdk1.8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemoArg {
    private String val;
    private int num;
    private List<String> ls;
}
