package com.framework.hanason.web.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sorata 2020-03-17 09:40
 */
public final class WebUtil {

    private WebUtil(){
        throw new AssertionError("工具类不能使用构造函数，请使用静态方法");
    }

    private static final String UNKNOWN = "UNKNOWN";

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !UNKNOWN.equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.contains(",")) {
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 静态获取 ServletRequestAttributes
     * @return ServletRequestAttributes
     */
    public static HttpServletRequest getRequest(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null){
            return null;
        }
        return requestAttributes.getRequest();
    }



    /**
     * web应用绝对路径
     *
     * @param request 请求对象
     * @return 绝对路径
     */
    public static String getBasePath(HttpServletRequest request) {
        String path = request.getContextPath();
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    }

    /**
     * 重定向
     * @param path 地址
     * @return 转发路径
     */
    public static String redirect(String path){
        return "redirect:"+path;
    }

    /**
     * 转发
     * @param path 地址
     * @return 转发地址
     */
    public static String forward(String path){
        return "forward:" + path;
    }

}
