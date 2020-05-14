package com.framework.hanason.core.exception;


import com.framework.hanason.core.domain.ResultData;
import com.framework.hanason.core.enums.ErrorCodeEnum;
/**
 * @author sorata 2020-03-16 11:31
 */
public class RootRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -3536936918322428985L;
    /**
     * 描述信息
     */
    private String msg;
    /**
     * 状态码
     */
    private Integer code;

    public RootRuntimeException(Integer code,String message){
        super(message);
        this.code = code;
        this.msg = message;
    }

    public RootRuntimeException(ErrorCodeEnum errorCodeEnum){
        this(errorCodeEnum.getCode(),errorCodeEnum.getMsg());
    }


    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }

    public ResultData toResult(){
        return ResultData.err(this.code,this.msg);
    }

}
