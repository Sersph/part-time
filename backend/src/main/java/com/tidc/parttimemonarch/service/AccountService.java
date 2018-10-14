package com.tidc.parttimemonarch.service;

import com.tidc.parttimemonarch.pojo.EnterpriseUser;
import com.tidc.parttimemonarch.pojo.PersonalUser;

import javax.validation.Valid;


public interface AccountService {

    //普通用户登陆
    PersonalUser personalSignIn(PersonalUser personalUser);

    //普通用户注册
    PersonalUser personalSignUp(PersonalUser personalUser);

    //企业用户注册
    EnterpriseUser enterpriseSignUp(@Valid EnterpriseUser enterpriseUser, String captcha);


    //企业用户登陆
    EnterpriseUser enterpriseSignIn(@Valid EnterpriseUser enterpriseUser);
}
