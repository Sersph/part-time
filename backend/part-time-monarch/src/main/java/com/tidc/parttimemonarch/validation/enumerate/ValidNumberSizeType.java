package com.tidc.parttimemonarch.validation.enumerate;

/**
 * @Author: 李家宝
 * @Date: 2018/10/29 23:08
 */
public enum ValidNumberSizeType {
    LENGTH(0), INTERVAL(1);

    private int type;

    ValidNumberSizeType(int type){
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
