package com.tidc.part.time.monarch.service;

import com.tidc.part.time.monarch.vo.RequestResult;

/**
 * 专门用于发送手机验证码的服务
 * @Author: 李家宝
 * @Date: 2018/11/5 18:30
 */
public interface PhoneService {

    /**
     * 用于发送手机验证码
     * 发送的验证码用 redis 来存储
     * 手机号为 key, code 为 value
     * @param phone
     * @return RequestResult
     */
    RequestResult sendPhoneVerificationCode(long phone);
}
