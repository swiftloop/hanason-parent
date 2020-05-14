package com.framework.hanason.web.jjwt;

import java.security.Key;

/**
 * @author sorata 2020-04-30 17:36
 *
 * jwt密钥的读取、存储
 */
public interface JwtKeyStore {

    /**
     * 保存一个key
     * @param key 密钥
     */
    void save(Key key);

    /**
     * 得到key
     * @return 密钥
     */
    Key get();


}
