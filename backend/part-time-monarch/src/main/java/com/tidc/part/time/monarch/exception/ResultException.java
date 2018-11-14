package com.tidc.part.time.monarch.exception;

import lombok.Data;

/**
 * @Author: 李家宝
 * @Date: 2018/10/29 12:04
 */
@Data
public class ResultException extends RuntimeException {

    private int code;


    public ResultException(int code, String message){
        super(message);
        this.code = code;
    }
}
