package com.tidc.parttimemonarch.service;

import com.tidc.parttimemonarch.domain.User;
import com.tidc.parttimemonarch.domain.UserInfo;
import com.tidc.parttimemonarch.security.UserTokenDTO;


public interface AccountService {

    //用户登陆
    UserTokenDTO signIn(User user);

    // 获取用户信息
    UserInfo getAccountInfo(String token);

    //普通用户注册
    void personalSignUp(User user);

    //企业用户注册
    void enterpriseSignUp(UserInfo userInfo, User user, String captcha);

    void logout(String token);
}
