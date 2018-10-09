package com.tidc.parttimemonarch.service.impl;

import com.tidc.parttimemonarch.enumerate.Code;
import com.tidc.parttimemonarch.result.RequestResult;
import com.tidc.parttimemonarch.util.EMailUtli;
import com.tidc.parttimemonarch.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.util.Random;

@Component
public class EMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public RequestResult sendMailCaptcha(String email, HttpSession session){
        String code = String.valueOf( new Random().nextInt(999999));
        boolean tab = EMailUtli.enterpriseSignInVerificationCode(email, code, javaMailSender);

        if (tab){
            SessionUtil.addSession(email + "code", code, session);
            return new RequestResult(Code.SUCCEED);
        }

        return new RequestResult(Code.ERROR);
    }
}