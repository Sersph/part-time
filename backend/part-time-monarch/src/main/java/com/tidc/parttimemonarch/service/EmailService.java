package com.tidc.parttimemonarch.service;

/**
 * @Author: 李家宝
 * @Date: 2018/10/28 21:57
 */
public interface EmailService {

    /**
     * 发送邮箱验证码
     * @param email
     */
    void sendMailCaptcha(String email);
}
