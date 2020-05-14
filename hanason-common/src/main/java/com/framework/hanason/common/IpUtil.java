package com.framework.hanason.common;

import java.util.regex.Pattern;

/**
 * @author sorata 2020-03-17 09:42
 */
public final class IpUtil {

    private IpUtil() {
        throw new AssertionError("工具类不能使用构造函数，请使用静态方法");
    }

    private static final long max = 4294967295L;

    /**
     *  抄的httpclient > InetAddressUtils
     */
    private static final String IPV4_BASIC_PATTERN_STRING =
            "(([1-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){1}" +
                    "(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){2}" +
                    "([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])";
    /**
     * 抄的httpclient > InetAddressUtils
     */
    private static final Pattern IPV4_PATTERN =
            Pattern.compile("^" + IPV4_BASIC_PATTERN_STRING + "$");

    /**
     * 是否是正确的ip地址
     *
     * @param input IP地址
     * @return true表示正确
     */
    static boolean isIpv4Address(final String input) {
        return IPV4_PATTERN.matcher(input).matches();
    }

    /**
     * 将文本的ip地址转化为long类型 方便存储
     *
     * @param ip IP地址
     * @return long ip
     */
    public static long toIpLong(String ip) {
        if (ip == null || ip.isEmpty()) {
            return 0;
        }
        if (!isIpv4Address(ip)) {
            return 0;
        }
        String[] split = ip.split("\\.");
        return Long.parseLong(split[0]) << 24 | Long.parseLong(split[1]) << 16 |
                Long.parseLong(split[2]) << 8 | Long.parseLong(split[3]);
    }

    /**
     * 将Long类型的IP地址转为文本型
     *
     * @param ip 转化为long的ip
     * @return ip
     */
    public static String toIpString(Long ip) {
        if (ip < 0 || ip > max ) {
            return "0.0.0.0";
        }

        return (ip >> 24) + "." + (ip >> 16 & 0xff) + "." +
                (ip >> 8 & 0xff) + "." + (ip & 0xff);
    }


}
