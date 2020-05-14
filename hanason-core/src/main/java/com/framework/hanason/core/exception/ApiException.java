package com.framework.hanason.core.exception;

/**
 * @author sorata 2020-03-16 11:44
 *
 * api接口的异常
 */
public class ApiException extends RootRuntimeException {

    private static final long serialVersionUID = -3068630547145728420L;

    public ApiException(Integer code, String message) {
        super(code, message);
    }
}
