package com.framework.hanason.common.proxy;

import java.lang.reflect.Proxy;

/**
 * @author sorata
 * @date 2019-08-01 11:01
 */
public final class ProxyFactory {

    public static Object getObject(Object t) {
        if (t == null) {
            throw new IllegalArgumentException("对象不能为空");
        }
        Class<?> tClass = t.getClass();
        return Proxy.newProxyInstance(tClass.getClassLoader(), tClass.getInterfaces(), (proxy, method, args) -> method.invoke(t, args));
    }


}
