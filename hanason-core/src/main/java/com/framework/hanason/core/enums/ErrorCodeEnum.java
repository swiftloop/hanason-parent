package com.framework.hanason.core.enums;


/**
 * @author sorata
 * @date 2019-11-08 11:17
 * <p>
 * 定义一些全局的 容易混淆的错误
 */
public enum ErrorCodeEnum{

    /**
     * 定义 一切与用户相关的错误 全都以 100开头  10001 表示用户被禁用 状态码以五位数
     */
    DISABLE_USER(10001, "用户被禁用"),
    NOY_FOUND_USER(10002, "未找到用户"),
    LOGIN_OTHER_DEVICE(10003, "用户在其他设备上登录"),
    USER_NOT_ROLE(10009, "您没有权限，请缴费"),

    /**
     * 一切内部错误全都以900开头 后两位表示状态 90000 表示系统错误
     */
    SYSTEM_ERROR(90000, "系统错误,请稍后再试"),


    NOT_FOUND_REQUEST(90001, "未找到request"),

    NOT_FOUND_AUTH_HEADER(90002, "未找到验证签名"),

    TOKEN_FORMAT_ERROR(90003, "token格式错误"),

    TOKEN_TIME_OUT(90004, "token超时"),

    TOKEN_VALID_ERROR(90005, "token验证失败"),

    SOCKET_NOT_LOGIN(90006, "socket未登录"),

    PARAM_SIGN_ERROR(90007, "参数签名错误"),

    PARAM_SIGN_TIMEOUT(90008, "参数签名超时"),

    NOT_FOUND_SIGN_PARAM(90009, "未找到签名参数"),

    KEY_HEADER_NOT_FOUND(90010, "无法找到密钥ID请求头"),

    NOT_CURR_KEY(90011, "不是当前使用的密钥库中的密钥");

    private final String msg;
    private final Integer code;

    ErrorCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }


}
