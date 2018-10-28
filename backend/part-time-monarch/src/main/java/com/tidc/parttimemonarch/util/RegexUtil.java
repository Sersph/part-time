package com.tidc.parttimemonarch.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    /**
     * 使用正则表达式对字符串进行验证
     * @param reg 传入正则
     * @param str 传入字符串
     * @return boolean 返回验证结果
     */
    public static boolean regex(String reg, String str){
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
