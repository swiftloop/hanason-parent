package com.framework.hanason.web.user;

import com.framework.hanason.web.jjwt.JwtAutoConfigure;
import com.framework.hanason.web.jjwt.JwtTemplate;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sorata 2020-05-07 17:30
 */
@Configuration
@ConditionalOnBean(JwtTemplate.class)
@AutoConfigureAfter(JwtAutoConfigure.class)
public class AuthorizationConfig {


    @Bean
    public AuthorizationApiPointcut authorizationApiPointcut(AuthorizationApiMethodInterceptor<IUser> advice) {
        AuthorizationApiPointcut pointcut = new AuthorizationApiPointcut();
        pointcut.setAdvice(advice);
        return pointcut;
    }

    @Bean
    public AuthorizationApiMethodInterceptor<IUser> authorizationAdvice(JwtTemplate jwtTemplate) {
        return new AuthorizationApiMethodInterceptor<>(jwtTemplate, IUser.class);
    }


}
