package com.tidc.parttimemonarch.service;

import com.tidc.parttimemonarch.result.RequestResult;
import com.tidc.parttimemonarch.model.PersonalUser;

import javax.servlet.http.HttpSession;

public interface AccountService {

    //获取用户信息
    RequestResult getUserInfo(HttpSession session);

    //用户登陆
    RequestResult personalSignUp(PersonalUser personalUser, HttpSession session);

    //用户注册
    RequestResult personalSignIn(PersonalUser personalUser, HttpSession session);

}
