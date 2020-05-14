package com.framework.hanason.web.user;

/**
 * @author sorata 2020-05-07 17:15
 *
 * 拥有jwt token请求头的http request 可以从token中解析出user
 *
 */
public class AuthUserHolder {

    private static final ThreadLocal<IUser> USER = new ThreadLocal<>();


    public static IUser getUser(){
        return USER.get();
    }

    public static void setUser(IUser user){
        USER.set(user);
    }

    public static void remove(){
        USER.remove();
    }

}
