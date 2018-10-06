package com.tidc.parttimemonarch.util;

import com.tidc.parttimemonarch.enumerate.Code;

public class UserUtil {


    /**
     * 验证用户信息
     * @param username
     * @param password
     * @return Code
     */
    public static Code validateUsernameAndPassword(String username, String password){
        if(validateUsername(username)){
            return Code.USERNAME_DO_NOT_CONFORM_TO_THE_RULES;
        }

        if (validatePassword(password)){
            return Code.PASSWORD_DO_NOT_CONFORM_TO_THE_RULES;
        }

        return Code.SUCCEED;
    }

    /**
     * 验证密码 6-20位字母数字组合
     * @param password
     * @return boolean 格式正确返回 true
     */
    private static boolean validatePassword(String password){
        String reg = "^[A-Za-z0-9]{6,20}$";
        return RegexUtli.regex(reg, password);
    }

    /**
     *  验证用户名 长度大于 5 小于 10
     * @param username
     * @return boolean 格式正确返回 true
     */
    private static boolean validateUsername(String username){
        return username.length() > 5 && username.length() < 10;
    }

}
