package com.framework.hanason.web.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author sorata 2019-12-10 17:28
 */
@Component
public final class SpringContextUtils {

    private final ApplicationContext applicationContext;

    @Autowired
    public SpringContextUtils(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }



    public <T> T getBean(Class<T> tClass){
        return applicationContext.getBean(tClass);
    }


    public <T>  T getBean(String claName,Class<T> tClass){
        return applicationContext.getBean(claName,tClass);
    }

    public Object getBean(String claName){
        return applicationContext.getBean(claName);
    }






}
