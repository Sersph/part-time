package com.tidc.parttimemonarch.controller;

import com.tidc.parttimemonarch.result.RequestResult;
import com.tidc.parttimemonarch.model.User;
import com.tidc.parttimemonarch.service.account.IAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Api(value = "普通用户注册登陆接口")
@ResponseBody
@RestController
@RequestMapping(value = "/account/personal")
public class AccountPersonalController {

    @Autowired
    private IAccountService accountService;


    @ApiOperation(value="注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "注册类型", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping(value = "/signUp")
    public RequestResult signUp(@Valid User user, HttpServletRequest httpServletRequest){
        System.out.println(user);
        HttpSession session = httpServletRequest.getSession();

        //判断注册类型 调用不同的注册方法
        if (user.getType() == 1){
            return accountService.useUsernameAndPasswordToSignUp(user, session);
        }

        return accountService.verifyPhoneNumberSignUp(user, session);

    }





    @ApiOperation(value="用户名密码登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "注册类型", required = true, dataType = "int", paramType = "query")
    })
    @PostMapping(value = "/signIn")
    public RequestResult signIn(User user, HttpServletRequest httpServletRequest){

        HttpSession session = httpServletRequest.getSession();
        return accountService.signIn(user, session);
    }

}