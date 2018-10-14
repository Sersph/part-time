package com.tidc.parttimemonarch.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 李家宝
 * @Date: 2018/10/14 16:08
 */
public class CookieUtil {

    private static final String cookieKey = "token";
    private static HttpServletRequest request = SessionUtil.getRequest();


    public static void addCookie(String key, HttpServletResponse response){
        Cookie cookie = new Cookie(cookieKey, key);
        cookie.setMaxAge(7200);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static void removeCookie(HttpServletResponse response){

        for (Cookie cookie : request.getCookies()){
            if (cookie.getName().equals(cookieKey)){
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
                break;
            }
        }
    }

    public static Cookie getCookie(){

        try {
            Cookie[] cookies = request.getCookies();
            for (int i = 0; i < cookies.length; i++){
                if (cookies[i].getName().equals(cookieKey)) {
                    return cookies[i];
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
