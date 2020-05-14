package com.framework.hanason.common;



/**
 * @author sorata 2020-03-17 11:04
 */
public final class RandomUtil {

    private RandomUtil() {
        throw new AssertionError("工具类不能使用构造函数，请使用静态方法");
    }

    /**
     * 获取一个随机的数字，位数不大于9
     *
     * @param len 数字的位数
     * @return int
     */
    public static int ofInt(int len) {
        if (len > 9) {
            throw new IllegalArgumentException("超出随机范围，最大只允许9");
        }
        double random = Math.random();
        while (random < 0.1f) {
            random = Math.random();
        }
        return (int) (random * Math.pow(10, len));
    }


    public static long ofLong(long len) {
        if (len > 15) {
            throw new IllegalArgumentException("超出随机范围，最大只允许15");
        }
        double random = Math.random();
        while (random < 0.1f) {
            random = Math.random();
        }
        return (long) (random * Math.pow(10, len));
    }


    public static String randStr(int len) {
        return String.valueOf(ofInt(len));
    }


}
