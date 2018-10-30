package com.tidc.parttimemonarch.vo;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class RequestResult implements Serializable {
    private int code;
    private String message;

    public void succeed(){
        this.code = 0;
        this.message = "成功";
    }

    public void error(int code, String message){
        this.code = code;
        this.message = message;
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
        return "RequestResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
