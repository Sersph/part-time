package com.tidc.parttimemonarch.service.account;

import com.tidc.parttimemonarch.result.RequestResult;
import com.tidc.parttimemonarch.model.User;

import javax.servlet.http.HttpSession;

public interface IAccountService {

    //获取用户信息
    RequestResult getUserInfo(HttpSession session);

    //用户登陆
    RequestResult signIn(User user, HttpSession session);

    //用户名密码注册
    RequestResult useUsernameAndPasswordToSignUp(User user, HttpSession session);

    //手机验证注册
    RequestResult verifyPhoneNumberSignUp(User user, HttpSession session);

}
