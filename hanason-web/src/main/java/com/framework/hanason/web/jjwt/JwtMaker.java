package com.framework.hanason.web.jjwt;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author sorata 2020-04-30 14:37
 */
@Slf4j
public abstract class JwtMaker {


    private static final long EXPIRE = TimeUnit.DAYS.toMillis(3);


    /**
     * 不要携带敏感信息
     *
     * @param entity 生成token信息的用户实体
     * @param key    密钥
     * @param <T>    范型
     * @return 签名
     */
    public static <T> String build(@NotNull(message = "生成用户token不能为空") T entity, @NotNull(message = "签名密钥不能为空") Key key) {
        return Jwts.builder().setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .setSubject(encode(JSON.toJSONBytes(entity)))
                .signWith(key).compact();
    }


    public static <T> T verify(@NotBlank(message = "签名不能为空") String token, @NotNull(message = "签名密钥不能为空") Key key, Class<T> tClass) {
        String subject = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
        if (subject == null || subject.isEmpty()) {
            throw new JwtException("token无效");
        }
        byte[] decode = decode(subject);
        if (decode == null){
            throw new JwtException("JwtMaker.verify无效的token: "+token);
        }
        return JSON.parseObject(decode, tClass);
    }

    /**
     * 简单的加密
     * @param bytes 需要转码的字节数组
     * @return 偏移后的字节数组
     */
    private static String encode(byte[] bytes) {
        byte[] chars = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            chars[i] = (byte) (~(bytes[i] + 'Z') & 0xff);
        }

        return Hex.encodeHexString(chars);
    }

    /**
     * 还原偏移
     * @param enc 经过偏移的字符串
     * @return 还原后的字节数组
     */
    private static byte[] decode(String enc) {
        try {
            byte[] bytes = Hex.decodeHex(enc);
            byte[] decodes = new byte[bytes.length];
            for (int i = 0; i < bytes.length; i++) {
                decodes[i] = (byte) ((~bytes[i] - 'Z') & 0xff);
            }
            return decodes;
        } catch (DecoderException e) {
            e.printStackTrace();
        }
        return null;
    }


}
