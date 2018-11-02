package com.tidc.parttimemonarch.logger;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AccountPersonalControllerLogger {

    private Logger logger = LoggerFactory.getLogger(AccountPersonalControllerLogger.class);

//    @Pointcut(value = "execution(public * com.tidc.parttimemonarch.controller.PersonalAccountController.personalSignIn(..))")
//    public void personalSignIn(){}
//
//    @Before(value = "personalSignIn()")
//    public void sigUpBefore(){
//        this.logger.info("用户注册开始执行");
//    }
//
//    @After(value = "personalSignIn()")
//    public void sigUpAfter(){
//        this.logger.info("用户注册执行完毕");
//    }

}
