package com.framework.hanason.web.utils;


import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sorata 2019-12-05 15:42
 * <p>
 * 参数解析工具
 */
public final class ParamUtils {

    private ParamUtils() {
        throw new IllegalStateException("参数工具类不能使用new，请使用其静态方法");
    }


    public static Map<String, String> readRequestParams(@Nonnull HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        HashMap<String, String> map = new HashMap<>(parameterMap.keySet().size());
        for (Map.Entry<String,String[]> entry : parameterMap.entrySet()){
            String[] values = entry.getValue();
            StringBuilder temp = new StringBuilder();
            for (int i = 0; i < values.length; i++) {
                temp.append(values[i]);
                if (i != values.length - 1) {
                    temp.append(",");
                }
            }
            map.put(entry.getKey(), temp.toString());
        }
        return map;
    }


}
