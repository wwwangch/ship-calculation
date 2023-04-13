package com.iscas.common.tools.junit5.testInstance.second;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * //TODO
 *
 * @author yuemingyang
 * @version 1.0
 * @date 2021/2/19 17:53
 * @since jdk1.8
 */
public interface EqualsContract<T> extends Testable {
    T createNotEqualValue();

    @Test
    default void valueEqualsItself() {
        T value = (T) createValue();
        assertEquals(value, value);
    }

    @Test
    default void valueDoesNotEqualNull() {
        T value = (T) createValue();
        assertFalse(value.equals(null));
    }

    @Test
    default void valueDoesNotEqualDifferentValue() {
        T value = (T) createValue();
        T differentValue = createNotEqualValue();
        assertNotEquals(value, differentValue);
        assertNotEquals(differentValue, value);
    }
}
