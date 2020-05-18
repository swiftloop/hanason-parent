package com.framework.hanason.web;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author sorata 2020-05-18 10:41
 */
@Configuration
@ConditionalOnMissingClass(value = {"freemarker.ext.servlet.FreemarkerServlet", "org.thymeleaf.Thymeleaf"})
public class WebMvcForExceptionConfig implements WebMvcConfigurer {


    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(new InternalResourceViewResolver("", ".html"));
    }
}
