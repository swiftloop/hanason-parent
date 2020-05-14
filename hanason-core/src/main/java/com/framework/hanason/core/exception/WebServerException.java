package com.framework.hanason.core.exception;

/**
 * @author sorata 2020-03-16 11:42
 *
 * web类的异常
 */
public class WebServerException extends RootRuntimeException  {

    private static final long serialVersionUID = 8995927223526036523L;

    public WebServerException(Integer code, String message) {
        super(code, message);
    }
}
