package com.tidc.part.time.monarch.vo;

import lombok.Data;

@Data
public class RequestResult {
    private int code;
    private String message;

    public RequestResult succeed(){
        this.code = 0;
        this.message = "成功";
        return this;
    }

    public RequestResult error(int code, String message){
        this.code = code;
        this.message = message;
        return this;
    }
}
