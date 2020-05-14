package com.framework.hanason.web.jjwt;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * @author sorata 2020-04-30 17:43
 */

@Slf4j
public class RedisJwtKeyStoreImpl implements JwtKeyStore {

    private Key currentKey;

    private final StringRedisTemplate stringRedisTemplate;
    private static final String JWT_STORE_KEY = "Jwt-store-key";


    public RedisJwtKeyStoreImpl(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void save(Key key) {
        if (key == null) {
            log.info("保存key失败,请检查创建的key");
            return;
        }
        String hexString = Hex.encodeHexString(key.getEncoded());
        stringRedisTemplate.opsForValue().set(JWT_STORE_KEY, hexString);
        currentKey = key;
    }

    @Override
    public Key get() {
        if (currentKey != null) {
            return currentKey;
        }
        String keyStr = stringRedisTemplate.opsForValue().get(JWT_STORE_KEY);
        if (keyStr == null || keyStr.isEmpty()) {
            //如果没有缓存key 则先生成一个key
            currentKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
            save(currentKey);
        } else {
            byte[] bytes;
            try {
                bytes = Hex.decodeHex(keyStr);
                currentKey = new SecretKeySpec(bytes, "HmacSHA256");
            } catch (DecoderException e) {
                log.error("RedisJwtKeyStoreImpl.get 得到缓存key hex失败,{}, 重新创建key", keyStr, e);
                currentKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
                save(currentKey);
            }
        }
        return currentKey;
    }


}
