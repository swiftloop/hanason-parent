package com.framework.hanason.web.jjwt;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

/**
 * @author sorata 2020-04-30 17:40
 */
public class LocalJwtKeyStoreImpl implements JwtKeyStore {

    private Key localKey;


    @Override
    public void save(Key key) {
        localKey = key == null ? localKey : key;
    }

    @Override
    public Key get() {
        if (localKey == null) {
            localKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        }
        return localKey;
    }
}
