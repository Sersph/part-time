package com.tidc.parttimemonarch.enumerate;

public enum Code {
    SUCCEED(0, "成功"),
    ERROR(1, "失败"),
    USER_NAME_ALREADY_EXIST(1001, "用户名已存在"),
    THE_USER_DOES_NOT_EXIST(1002, "该用户不存在"),
    INCORRECT_USERNAME_AND_PASSWORD(1003, "用户名密码不正确"),
    USER_NOT_LOG_IN(1004, "用户未登录"),
    USER_LOGGER_IN(1005, "用户已登陆"),
    REGISTRATION_TYPE_CANNOT_BE_FOUND(1006, "注册类型找不到"),
    PASSWORD_DO_NOT_CONFORM_TO_THE_RULES(1007, "密码不符合规则， 密码必须是6-20位字母数字组合"),
    USERNAME_DO_NOT_CONFORM_TO_THE_RULES(1008, "用户名不符合规则， 用户名长度必须大于5小于10");

    private int code;
    private String message;
    private Code(int code, String message){
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

