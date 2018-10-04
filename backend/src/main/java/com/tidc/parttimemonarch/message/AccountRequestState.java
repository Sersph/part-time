package com.tidc.parttimemonarch.message;

import com.tidc.parttimemonarch.enumerate.Code;
import com.tidc.parttimemonarch.model.User;

public class AccountRequestState extends RequestState {
    private User user;
    public AccountRequestState(Code code, User user) {
        super(code);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "{" +
                "code=" + this.getCode() +
                "message=" + this.getMessage() +
                "user=" + user +
                '}';
    }
}

