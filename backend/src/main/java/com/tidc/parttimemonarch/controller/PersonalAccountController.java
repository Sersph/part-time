package com.tidc.parttimemonarch.controller;

import com.tidc.parttimemonarch.message.RequestState;
import com.tidc.parttimemonarch.model.User;
import com.tidc.parttimemonarch.service.PersonalSignUpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "兼职君 Personal Account 接口")
@RestController
@RequestMapping(value = "/personal/account")
public class PersonalAccountController {

    @Autowired
    private PersonalSignUpService personalSignUpService;

    @ApiOperation(value="用户注册接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "注册类型", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping(value = "/signUp/{type}")
    public RequestState signUp(@PathVariable(value = "type") int type ,User user){

        //判断注册类型 调用不同的注册方法
        return personalSignUpService.signUp(type, user);
    }
}
