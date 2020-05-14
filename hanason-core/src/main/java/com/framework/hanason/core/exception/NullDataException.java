package com.framework.hanason.core.exception;

/**
 * @author sorata 2020-03-16 11:37
 *
 * 包装null异常
 */
public class NullDataException extends RootRuntimeException {

    private static final long serialVersionUID = 8406221586082784179L;

    public NullDataException(Integer code, String message) {
        super(code, message);
    }
}
