package com.tidc.parttimemonarch.util;
import javax.servlet.http.HttpSession;

public class SessionUtil {


    public static boolean addSession(String key, Object object, HttpSession session){
        session.setAttribute(key, object);
        System.out.println(session.getAttribute(key));
        return isSession(key, session);
    }


    public static boolean isSession(String key, HttpSession session){
        if (session.getAttribute(key) != null){
            return true;
        }
        return false;
    }

    public static boolean removeSession(String key, HttpSession session){
        session.removeAttribute(key);
        return !isSession(key, session);
    }
}
