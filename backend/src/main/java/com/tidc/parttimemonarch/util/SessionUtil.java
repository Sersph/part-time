package com.tidc.parttimemonarch.util;

import com.tidc.parttimemonarch.model.User;

import javax.servlet.http.HttpSession;

public class SessionUtil {
    public final static String SESSION_KEY = "user";



    public static boolean addSession(User user, HttpSession session){
        session.setAttribute(SESSION_KEY, user);
        return islogIn(session);
    }


    public static boolean islogIn(HttpSession session){
        if (session.getAttribute(SESSION_KEY) != null){
            return true;
        }

        return false;
    }

    public static boolean removeSession(HttpSession session){
        session.removeAttribute(SESSION_KEY);
        return !islogIn(session);
    }
}
