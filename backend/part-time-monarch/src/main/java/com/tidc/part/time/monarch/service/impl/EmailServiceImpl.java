package com.tidc.part.time.monarch.service.impl;

import com.tidc.part.time.monarch.exception.ResultException;
import com.tidc.part.time.monarch.mapper.UserInfoMapper;
import com.tidc.part.time.monarch.service.EmailService;
import com.tidc.part.time.monarch.util.EMailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 * 发送邮件验证码
 * @author baoer
 */
@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 用于发送企业用户注册时的邮箱验证码
     * @param email
     * @return
     */
    @Override
    public void sendEnterpriseSignUpMainCode(String email){

        String emailRex = "^\\w+@(\\w+\\.)+\\w+$";

        //判断 邮箱格式
        if (!email.matches(emailRex)){
            throw new ResultException(1007, "邮箱格式错误");
        }

        if(this.userInfoMapper.selectUserInfoByEmail(email) != null){
            throw new ResultException(1001, "该邮箱已被注册");
        }

        //随机生成六位验证码
        String code = String.valueOf( new Random().nextInt(999999));

        while (true) {
            if (code.length() == 6){
                break;
            }
            code = String.valueOf( new Random().nextInt(999999));
        }

        if(!EMailUtil.sendMailCaptcha(email, code, javaMailSender)){
            throw new ResultException(1002, "邮箱不存在");
        }


        this.redisTemplate.opsForValue().set(email, code, 300, TimeUnit.SECONDS);

    }

    @Override
    public int informEnterprise(String email, String partTimeName) {
        System.err.println("EmailServiceImpl informEnterprise" + email);
        if(!EMailUtil.informEnterprise(email, partTimeName, javaMailSender)){
            throw new ResultException(1002, "邮箱不存在");
        }
        return 0;
    }


}