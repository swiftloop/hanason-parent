package com.framework.hanason.core.exception;

/**
 * @author sorata 2020-03-24 15:22
 *
 * 数字异常
 */
public class NumberAccessException extends RootRuntimeException {

    private static final long serialVersionUID = 818469630523622213L;

    public NumberAccessException(Integer code, String message) {
        super(code, message);
    }
}
