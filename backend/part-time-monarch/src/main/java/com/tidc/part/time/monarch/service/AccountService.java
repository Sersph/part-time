package com.tidc.part.time.monarch.service;

import com.tidc.part.time.monarch.security.UserTokenDTO;
import com.tidc.part.time.monarch.domain.User;
import com.tidc.part.time.monarch.domain.UserInfo;


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
