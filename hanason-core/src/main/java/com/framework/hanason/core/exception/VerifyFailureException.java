package com.framework.hanason.core.exception;

/**
 * @author sorata 2020-03-16 11:39
 *
 * 验证类的异常
 */
public class VerifyFailureException extends RootRuntimeException {

    private static final long serialVersionUID = 2679478284262378717L;

    public VerifyFailureException(Integer code, String message) {
        super(code, message);
    }
}
