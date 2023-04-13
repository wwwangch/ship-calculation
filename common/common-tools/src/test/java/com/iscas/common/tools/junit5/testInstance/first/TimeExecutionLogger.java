package com.iscas.common.tools.junit5.testInstance.first;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * //TODO
 *
 * @author yuemingyang
 * @version 1.0
 * @date 2021/2/19 17:41
 * @since jdk1.8
 */
@Tag("timed")
@ExtendWith(TimingExtension.class)
public interface TimeExecutionLogger {
}
