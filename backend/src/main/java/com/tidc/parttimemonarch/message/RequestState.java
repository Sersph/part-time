package com.tidc.parttimemonarch.message;

import com.tidc.parttimemonarch.enumerate.Code;

public class RequestState {
    private int code;
    private String message;


    public RequestState() {
        super();
    }

    public RequestState(Code code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "RequestState{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
