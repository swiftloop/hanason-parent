package com.framework.hanason.common;

/**
 * @author sorata 2020-04-21 09:56
 */
public abstract class DefaultValueUtil {


    public static <T,M extends T> T value(T origin,M defaultValue){
        return origin == null ? defaultValue : origin;
    }


}
