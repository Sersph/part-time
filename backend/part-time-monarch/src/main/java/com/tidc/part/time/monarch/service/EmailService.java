package com.tidc.part.time.monarch.service;

/**
 * @Author: 李家宝
 * @Date: 2018/10/28 21:57
 */
public interface EmailService {

    /**
     * 用于发送企业用户注册时的邮箱验证码
     * @param email
     */
    void sendEnterpriseSignUpMainCode(String email);

    /**
     * 用于通知企业用户他们发布的兼职有人申请了
     * @param email
     * @param partTimeName
     * @return int
     */
    int informEnterprise(String email, String partTimeName);

}
