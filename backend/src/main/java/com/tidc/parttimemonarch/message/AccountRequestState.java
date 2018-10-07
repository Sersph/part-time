package com.tidc.parttimemonarch.message;

import com.tidc.parttimemonarch.enumerate.Code;
import com.tidc.parttimemonarch.model.User;

public class AccountRequestState extends RequestState {
    private User userInfo;

    public AccountRequestState(Code code, User userInfo) {
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
                "message=" + this.getMessage() +
                "userInfo=" + userInfo +
                '}';
    }
}

