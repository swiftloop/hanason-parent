package com.framework.hanason.web.jjwt;

import javax.validation.constraints.NotNull;

/**
 * @author sorata 2020-05-06 10:19
 */
public class JwtTemplate {


    private JwtKeyStore keyStore;


    public JwtTemplate(JwtKeyStore jwtKeyStore) {
        this.keyStore = jwtKeyStore;
    }


    public <T> String create(@NotNull T entity) {
        return JwtMaker.build(entity, keyStore.get());
    }

    public <T> T verify(String token, Class<T> tClass) {
        return JwtMaker.verify(token, keyStore.get(), tClass);
    }


}
