package com.iscas.common.nexus.tools.exception;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/1/19 9:50
 */
public class NexusException extends Exception {
    public NexusException() {
    }

    public NexusException(String message) {
        super(message);
    }


    public NexusException(String message, Throwable cause) {
        super(message, cause);
    }


    public NexusException(Throwable cause) {
        super(cause);
    }
}
