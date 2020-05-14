package com.framework.hanason.core.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author sorata
 */
@Data
public class ResultData<T> implements Serializable {

    private static final Integer OK_CODE = 200;
    private static final String OK_MSG = "OK";
    private static final Integer ERROR_CODE = 500;
    private static final String ERROR_MSG = "ERROR";


    public static final ResultData OK = ResultData.ok();
    public static final ResultData ERROR = ResultData.err();


    private Integer code;

    private String message;

    private T data;


    public ResultData(Integer code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }


    public static <T> ResultData<T> ok(Integer code, T data, String message) {
        return new ResultData<>(code, data, message);
    }

    public static <T> ResultData<T> ok(Integer code, T data) {
        return ok(code, data, OK_MSG);
    }


    public static <T> ResultData<T> ok(T data) {
        return ok(OK_CODE, data);
    }

    public static ResultData ok(){
        return ok(null);
    }



    public static <T> ResultData<T> okMsg(String message) {
        return ok(OK_CODE, null, message);
    }


    public static <T> ResultData err(Integer code, T data, String message) {
        return new ResultData<>(code, data, message);
    }

    public static ResultData err(Integer code, String message) {
        return err(code, null, message);
    }

    public static ResultData err(String message){
        return err(ERROR_CODE,message);
    }

    public static ResultData err(){
        return err(ERROR_MSG);
    }


    public static <K,V> ResultData result(Result<K,V> result){
        Objects.requireNonNull(result);
        HashMap<K, V> map = new HashMap<>(8);
        result.apply(map);
        return ok(result);
    }


    @FunctionalInterface
    public interface Result<K,V>{

        void apply(Map<K,V> map);
    }



}
