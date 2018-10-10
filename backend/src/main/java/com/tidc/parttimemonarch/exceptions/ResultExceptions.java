package com.tidc.parttimemonarch.exceptions;


public class ResultExceptions extends RuntimeException {

    private int code;


    public ResultExceptions(int code, String message){
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
