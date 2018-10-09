package com.tidc.parttimemonarch.controller;

import com.tidc.parttimemonarch.enumerate.Code;
import com.tidc.parttimemonarch.result.RequestResult;
import com.tidc.parttimemonarch.service.impl.EMailService;
import com.tidc.parttimemonarch.service.AccountService;
import com.tidc.parttimemonarch.util.SessionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value = "用户状态接口")
@RestController
@RequestMapping(value = "/account")
public class AccountController {


    @Autowired
    private AccountService accountService;

    @Autowired
    private EMailService eMailService;


    @ApiOperation(value="获取用户信息")
    @GetMapping(value = "/userInfo")
    public RequestResult getUserInfo(HttpServletRequest httpServletRequest){
        return this.accountService.getUserInfo(httpServletRequest.getSession());
    }


    @ApiOperation(value = "退出登陆")
    @GetMapping(value = "/signOut")
    public RequestResult exit(HttpServletRequest httpServletRequest){
        if (SessionUtil.removeSession("user", httpServletRequest.getSession())){
            return new RequestResult(Code.SUCCEED);
        }

        return new RequestResult(Code.ERROR);
    }


    @ApiOperation(value = "发送邮件验证码")
    @ApiImplicitParam(name = "email", value = "邮箱", required = true, dataType = "String", paramType = "header")
    @PostMapping(value = "/sendMailCaptcha")
    public RequestResult sendMailCaptcha(@RequestHeader(value = "email") String email, HttpServletRequest httpServletRequest){
        return this.eMailService.sendMailCaptcha(email, httpServletRequest.getSession());
    }
}




























