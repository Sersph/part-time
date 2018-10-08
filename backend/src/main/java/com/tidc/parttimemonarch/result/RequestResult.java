package com.tidc.parttimemonarch.result;

import com.tidc.parttimemonarch.enumerate.Code;

public class RequestResult {
    private int code;
    private String message;


    public RequestResult() {
        super();
    }

    public RequestResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public RequestResult(Code code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public int getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "RequestResult{" +
                "code=" + code +
                ", result='" + message + '\'' +
                '}';
    }
}
