package com.tidc.parttimemonarch.message;

import com.tidc.parttimemonarch.enumerate.Code;

public class RequestState {
    private int code;
    private String message;


    public RequestState() {
        super();
    }

    public RequestState(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public RequestState(Code code) {
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
        return "RequestState{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
