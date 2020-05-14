package com.framework.hanason.core.exception;

import com.framework.hanason.core.enums.ErrorCodeEnum;

/**
 * @author sorata 2020-03-16 11:43
 *
 * 服务器端异常
 */
public class ServerException extends RootRuntimeException {

    private static final long serialVersionUID = -7033221784090985691L;

    public ServerException(Integer code, String message) {
        super(code, message);
    }

    public ServerException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum);
    }
}
