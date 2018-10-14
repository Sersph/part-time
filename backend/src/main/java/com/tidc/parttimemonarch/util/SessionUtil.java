package com.tidc.parttimemonarch.util;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {

    private static HttpSession session;

    private static HttpServletRequest request;
    static {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        request = servletRequestAttributes.getRequest();
        session = servletRequestAttributes.getRequest().getSession();
    }


    public static void addSession(String key, Object value){
        session.setAttribute(key, value);
    }


    public static boolean isSession(String key){
        if (session.getAttribute(key) != null){
            return true;
        }
        return false;
    }

    public static boolean removeSession(String key){
        session.removeAttribute(key);
        return !isSession(key);
    }

    public static Object getSessionAttribute(String key){
        return session.getAttribute(key);
    }

    public static HttpServletRequest getRequest(){
        return request;
    }


}
