package com.tidc.parttimemonarch.service;

import com.tidc.parttimemonarch.model.PersonalUser;

import javax.servlet.http.HttpSession;

public interface AccountService {

    //获取用户信息
    Object getUserInfo(HttpSession session);

    //用户登陆
    void personalSignUp(PersonalUser personalUser, HttpSession session);

    //用户注册
    void personalSignIn(PersonalUser personalUser, HttpSession session);

}
