package com.framework.hanason.web.user;

import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.lang.NonNull;

import java.lang.reflect.Method;

/**
 * @author sorata 2020-05-07 17:25
 */
public class AuthorizationApiPointcut extends StaticMethodMatcherPointcutAdvisor {
    private static final long serialVersionUID = -8501402732947903047L;

    @Override
    public boolean matches(@NonNull Method method, @NonNull Class<?> aClass) {
        AuthorizationApi annotation = aClass.getAnnotation(AuthorizationApi.class);
        if (annotation != null){
            return true;
        }
        return method.getAnnotation(AuthorizationApi.class) != null;
    }
}
