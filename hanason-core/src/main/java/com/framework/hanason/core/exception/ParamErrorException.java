package com.framework.hanason.core.exception;

/**
 * @author sorata 2020-03-16 11:38
 * 参数错误
 */
public class ParamErrorException extends RootRuntimeException {

    private static final long serialVersionUID = 5508726286336678823L;

    public ParamErrorException(Integer code, String message) {
        super(code, message);
    }
}
