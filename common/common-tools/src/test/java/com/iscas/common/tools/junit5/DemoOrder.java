package com.iscas.common.tools.junit5;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * //顺序测试
 *
 * @author yuemingyang
 * @version 1.0
 * @date 2021/2/18 15:30
 * @since jdk1.8
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DemoOrder {
    @Test
//    @Order(1)
    void nullValues() {
        // perform assertions against null values
    }

    @Test
//    @Order(2)
    void emptyValues() {
        // perform assertions against empty values
    }

    @Test
//    @Order(3)
    void validValues() {
        // perform assertions against valid values
    }
}
