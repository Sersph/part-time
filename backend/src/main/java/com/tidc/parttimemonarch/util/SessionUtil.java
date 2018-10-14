package com.tidc.parttimemonarch.util;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.net.HttpCookie;

public class SessionUtil {

    private static HttpSession session;

    private static HttpCookie cookie;

    static {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        session = servletRequestAttributes.getRequest().getSession();
    }

    public static void signUpSession(Object object){
        addSession("user", object);
    }


    public static void addSession(String key, Object object){
        SessionUtil.session.setAttribute(key, object);
    }


    public static boolean isSession(String key){
        if (SessionUtil.session.getAttribute(key) != null){
            return true;
        }
        return false;
    }

    public static boolean removeSession(String key){
        SessionUtil.session.removeAttribute(key);
        return !isSession(key);
    }

    public static Object getSessionAttribute(String key){
        return session.getAttribute(key);
    }

    private static HttpSession getSession(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes.getRequest().getSession();
    }
}
