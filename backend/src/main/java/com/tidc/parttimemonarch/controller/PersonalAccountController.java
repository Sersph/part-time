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
import javax.validation.Valid;

@Api(value = "普通用户注册登陆接口")
@ResponseBody
@RestController
@RequestMapping(value = "/account/personal")
public class PersonalAccountController {

    @Autowired
    private PersonalSignUpService personalSignUpService;
    @Autowired
    private PersonalSignInService personalSignInService;


    @ApiOperation(value="用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "注册类型", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping(value = "/signUp")
    public RequestState signUp(@Valid User user, HttpServletRequest httpServletRequest){
        System.out.println(user);
        HttpSession session = httpServletRequest.getSession();
        //判断注册类型 调用不同的注册方法
        return personalSignUpService.signUp(user, session);
    }





    @ApiOperation(value="用户名密码登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping(value = "/signIn")
    public RequestState signIn(User user, HttpServletRequest httpServletRequest){

        HttpSession session = httpServletRequest.getSession();
        return personalSignInService.signIn(user, session);
    }

}
