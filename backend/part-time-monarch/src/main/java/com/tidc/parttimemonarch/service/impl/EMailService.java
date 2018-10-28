package com.tidc.parttimemonarch.service.impl;

import com.tidc.parttimemonarch.exceptions.ResultExceptions;
import com.tidc.parttimemonarch.mapper.UserInfoMapper;
import com.tidc.parttimemonarch.util.EMailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;


@Component
public class EMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserInfoMapper userInfoMapper;
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

        if(this.userInfoMapper.selectUserInfoByEmail(email) != null){
            throw new ResultExceptions(1001, "该邮箱已被注册");
        }


        //随机生成六位验证码
        String code = String.valueOf( new Random().nextInt(999999));

        if(!EMailUtil.sendMailCaptcha(email, code, javaMailSender)){

            throw new ResultExceptions(1002, "邮箱不存在");
        }

        System.out.println(code);

        this.redisTemplate.opsForValue().set(email, code, 300, TimeUnit.SECONDS);

        System.out.println(email + "code");
    }
}