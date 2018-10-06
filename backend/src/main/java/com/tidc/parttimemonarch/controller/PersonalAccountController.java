package com.tidc.parttimemonarch.controller;

import com.tidc.parttimemonarch.message.RequestState;
import com.tidc.parttimemonarch.model.User;
import com.tidc.parttimemonarch.service.account.PersonalSignInService;
import com.tidc.parttimemonarch.service.account.PersonalSignUpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Api(value = "兼职君 Personal Account 接口")
@RestController
@RequestMapping(value = "/personal/account")
public class PersonalAccountController {

    @Autowired
    private PersonalSignUpService personalSignUpService;
    @Autowired
    private PersonalSignInService personalSignInService;


    @ApiOperation(value="用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "注册类型", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping(value = "/signUp/{type}")
    public RequestState signUp(@PathVariable(value = "type") int type , User user, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        //判断注册类型 调用不同的注册方法
        return personalSignUpService.signUp(type, user, session);
    }







    @ApiOperation(value="用户名密码登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping(value = "/signIn/1")
    public RequestState signIn(@RequestParam(value = "username") String username,
                               @RequestParam(value = "password") String password,
                               HttpServletRequest httpServletRequest){

        HttpSession session = httpServletRequest.getSession();
        return personalSignInService.signIn(username, password, session);
    }

    @ApiOperation(value="验证手机登陆")
    @PostMapping(value = "/signIn/2")
    public RequestState MobileAuthenticationLogin(){
        return null;
    }

}
