package com.tidc.part.time.monarch.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * @Author: 李家宝
 * @Date: 2018/11/12 10:54
 */
@Aspect
@Component
public class ControllerLogger {

    public Logger logger = LoggerFactory.getLogger(ControllerLogger.class);

    @Before(value = "execution(* com.tidc.part.time.monarch.controller.*.*(..))")
    public void controllerLogger(JoinPoint joinPoint){

        this.logger.info("method name is [" + joinPoint.getSignature().getName() + "]");
        System.err.println(joinPoint.getSignature().getName());
//        for (Object arg : joinPoint.getArgs()) {
//            this.logger.info(arg.toString());
//        }

    }
}
