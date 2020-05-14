package com.framework.hanason.web.jjwt;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * @author sorata 2020-05-06 09:59
 */
public class ConstantJwtKeyStoreImpl implements JwtKeyStore {

    private static final String KEYS = "1f19d7061aabac85495907c7df4e44bd31f5c7757e90487594251b88fb31f00e";

    private Key localKey;

    @Override
    public void save(Key key) {
        localKey = key == null ? localKey : key;
    }

    @Override
    public Key get() {
        if (localKey == null) {
            try {
                localKey = new SecretKeySpec(Hex.decodeHex(KEYS), "HmacSHA256");
            } catch (DecoderException e) {
                e.printStackTrace();
            }
        }
        return localKey;
    }


}
