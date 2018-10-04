package com.tidc.parttimemonarch.enumerate;

public enum AccountCode {
    SUCCEED(0, "成功"),
    USER_NAME_ALREADY_EXIST(1001, "用户名已存在"),
    INCORRECT_PASSWORD(1002, "密码不正确"),
    USER_NOT_LOG_IN(1003, "用户未登录");

    private int code;
    private String message;
    private AccountCode(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode(){
        return this.code;
    }

    public String getMessage(){
        return this.message;
    }

    @Override
    public String toString() {
        return "{" +
                "code:\"" + code + "\"" +
                ", message:\"" + message + "\"" +
                '}';
    }
}

