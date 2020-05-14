package com.framework.hanason.core.exception;

/**
 * @author sorata 2020-03-27 18:24
 */
public class IllegalArgumentException extends RootRuntimeException {


    private static final long serialVersionUID = 2781926297202000413L;

    public IllegalArgumentException(Integer code, String message) {
        super(code, message);
    }
}
