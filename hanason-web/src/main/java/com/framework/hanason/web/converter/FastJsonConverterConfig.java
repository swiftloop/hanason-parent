package com.framework.hanason.web.converter;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author sorata 2020-05-06 14:01
 */
@Configuration
@ConditionalOnClass(FastJsonHttpMessageConverter.class)
public class FastJsonConverterConfig implements WebMvcConfigurer {


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //移除jackson
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter){
                converters.remove(converter);
            }
        }
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        converters.add(converter);
    }






}
