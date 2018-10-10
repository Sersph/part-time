package com.tidc.parttimemonarch.service.impl;

import com.tidc.parttimemonarch.exceptions.ResultExceptions;
import com.tidc.parttimemonarch.util.EMailUtil;
import com.tidc.parttimemonarch.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Random;


@Component
public class EMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 发送邮箱验证码
     * @param email
     * @return
     */
    public void sendMailCaptcha(String email){

        //判断 邮箱格式
        if (!email.matches("^\\w+@(\\w+\\.)+\\w+$")){
            throw new ResultExceptions(1007, "邮箱格式错误");
        }

        //随机生成六位验证码
        String code = String.valueOf( new Random().nextInt(999999));

        if(!EMailUtil.sendMailCaptcha(email, code, javaMailSender)){

            throw new ResultExceptions(1002, "邮箱不存在");
        }

        RedisUtil.set(email + "verificationCode", code, 300, stringRedisTemplate);
    }









    public Object getVerificationCode(String email){
        String key = email + "verificationCode";
        return RedisUtil.getValueByKey(key, this.stringRedisTemplate);
    }
}