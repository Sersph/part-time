package com.tidc.parttimemonarch.util;
import javax.servlet.http.HttpSession;

public class SessionUtil {


    public static void signUpSession(Object object, HttpSession session){
        addSession("user", object, session);
    }


    public static void addSession(String key, Object object, HttpSession session){
        session.setAttribute(key, object);
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
