package com.tidc.parttimemonarch.util;

import com.tidc.parttimemonarch.exceptions.ResultExceptions;

/**
 * @Author: 李家宝
 * @Date: 2018/10/12 18:30
 */
public class NumberUtil {


    public static void validNumberLength(long number, int least, int big, String message){

        if (number <= 0){
            throw new ResultExceptions(1005, "不能为零");
        }

        int numberLength = (number + "").length();

        if (numberLength < least || numberLength > big){
            throw new ResultExceptions(1005, message);
        }

    }
}
