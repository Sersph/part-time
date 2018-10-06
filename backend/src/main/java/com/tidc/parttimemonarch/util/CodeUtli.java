package com.tidc.parttimemonarch.util;

import com.tidc.parttimemonarch.enumerate.Code;

public class CodeUtli {

    /**
     * 判断是不是成功
     * @param code
     * @return
     */
    public static boolean isSuccceed(Code code){
        if (code == Code.SUCCEED){
            return true;
        }
        return false;
    }
}
