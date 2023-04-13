package com.iscas.common.jgit.tools.exception;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/11/24 10:29
 * @since jdk1.8
 */
public class JGitException extends Exception {
    public JGitException() {
        super();
    }

    public JGitException(String message) {
        super(message);
    }

    public JGitException(String message, Throwable cause) {
        super(message, cause);
    }

    public JGitException(Throwable cause) {
        super(cause);
    }
}
