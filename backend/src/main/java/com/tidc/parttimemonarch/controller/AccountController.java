package com.tidc.parttimemonarch.controller;

import com.tidc.parttimemonarch.result.RequestResult;
import com.tidc.parttimemonarch.result.RequestResultImpl;
import com.tidc.parttimemonarch.service.impl.EMailService;
import com.tidc.parttimemonarch.service.AccountService;
import com.tidc.parttimemonarch.util.SessionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "用户接口")
@RestController
@RequestMapping(value = "/account")
public class AccountController {


    @Autowired
    private AccountService accountService;

    @Autowired
    private EMailService eMailService;

    @Autowired
    private RequestResultImpl result;


    @ApiOperation(value="获取用户信息")
    @GetMapping(value = "/userInfo")
    public RequestResult getUserInfo(HttpServletRequest httpServletRequest){
        this.result.succeed(this.accountService.getUserInfo(httpServletRequest.getSession()));
        return this.result;
    }


    @ApiOperation(value = "退出登陆")
    @GetMapping(value = "/signOut")
    public RequestResultImpl exit(HttpServletRequest httpServletRequest){
        if (!SessionUtil.removeSession("user", httpServletRequest.getSession())){
            this.result.error(1, "失败");
        }else{
            this.result.setAccountInfo(null);
            this.result.succeed();
        }

        return this.result;
    }


    @ApiOperation(value = "发送邮件验证码")
    @ApiImplicitParam(name = "email", value = "邮箱", required = true, dataType = "String", paramType = "header")
    @PostMapping(value = "/sendMailCaptcha")
    public RequestResultImpl sendMailCaptcha(@RequestHeader(value = "email") String email){

        this.eMailService.sendMailCaptcha(email);
        this.result.succeed();
        return this.result;
    }
}




























