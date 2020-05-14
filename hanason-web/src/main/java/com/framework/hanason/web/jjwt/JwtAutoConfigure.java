package com.framework.hanason.web.jjwt;

import io.jsonwebtoken.Jwts;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sorata 2020-05-06 10:13
 */
@Configuration
@ConditionalOnClass(Jwts.class)
public class JwtAutoConfigure {



    @Bean
    @ConditionalOnMissingBean(value = JwtKeyStore.class)
    public JwtKeyStore keyStore(){
        return new ConstantJwtKeyStoreImpl();
    }


    @Bean
    @ConditionalOnMissingBean
    public JwtTemplate jwtTemplate(JwtKeyStore jwtKeyStore){
        return new JwtTemplate(jwtKeyStore);
    }



}
