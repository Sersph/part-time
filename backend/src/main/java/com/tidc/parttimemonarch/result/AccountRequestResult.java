package com.tidc.parttimemonarch.result;

import com.tidc.parttimemonarch.enumerate.Code;
import com.tidc.parttimemonarch.model.User;

public class AccountRequestResult extends RequestResult {
    private User userInfo;

    public AccountRequestResult(Code code, User userInfo) {
        super(code);
        this.userInfo = userInfo;
    }

    public User getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "{" +
                "code=" + this.getCode() +
                "result=" + this.getMessage() +
                "userInfo=" + userInfo +
                '}';
    }
}

