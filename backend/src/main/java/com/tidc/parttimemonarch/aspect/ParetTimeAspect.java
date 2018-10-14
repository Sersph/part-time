package com.tidc.parttimemonarch.aspect;

import com.tidc.parttimemonarch.exceptions.ResultExceptions;
import com.tidc.parttimemonarch.pojo.EnterpriseUser;
import com.tidc.parttimemonarch.util.CookieUtil;
import com.tidc.parttimemonarch.util.SessionUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 *
 * 企业用户操作权限切面
 *
 * @Author: 李家宝
 * @Date: 2018/10/12 20:04
 */
@Aspect
@Component
public class ParetTimeAspect {

    @Pointcut("execution(public * com.tidc.parttimemonarch.controller.PartTimeController.addPartTime(..))")
    public void verifyUserPermissions(){}

    @Before("verifyUserPermissions()")
    public void verifyUserPermissionsBefore(){

        if (!SessionUtil.isSession(CookieUtil.getCookie().getValue())){
            throw new ResultExceptions(1003, "用户未登陆");
        }
        try {
            EnterpriseUser user = (EnterpriseUser) SessionUtil.getSessionAttribute("user");
        }catch (Exception e){
            throw new ResultExceptions(1006, "您的用户权限不足，不可以发布兼职信息");
        }

    }

}
