package com.framework.hanason.common;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author sorata 2020-03-24 15:08
 */
public class IdGenerator {

    private static final ZoneId ZONE_ID = ZoneId.of("UTC+8");

    private static final char[] NUMBERS = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

    private static final char[] CHARS = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'};

    private static final char[] ALL;

    private IdGenerator() {
        throw new IllegalStateException("do not init");
    }

    static {
        ALL = new char[NUMBERS.length + CHARS.length];
        System.arraycopy(NUMBERS, 0, ALL, 0, NUMBERS.length);
        System.arraycopy(CHARS, 0, ALL, NUMBERS.length, CHARS.length);
    }

    /**
     * 创建一个随机的订单号  不包括tag 长度为34
     *
     * @param tag 标识
     * @return 订单号
     */
    public static String getOrderId(String tag) {
        return tag + DateTimeFormatter.ofPattern("yyyyMMddHHmmss", Locale.CHINA).
                format(LocalDateTime.now(ZONE_ID)) + randStr(20);
    }

    public static String randStr(int len) {
        if (len <= 0) {
            return "";
        }
        char[] chars = new char[len];
        for (int i = 0; i < len; i++) {
            chars[i] = ALL[ThreadLocalRandom.current().nextInt(ALL.length)];
        }
        return new String(chars);
    }


}
