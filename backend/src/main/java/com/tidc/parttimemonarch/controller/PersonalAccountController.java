package com.tidc.parttimemonarch.controller;

import com.tidc.parttimemonarch.message.RequestState;
import com.tidc.parttimemonarch.model.User;
import com.tidc.parttimemonarch.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@Api(value = "兼职君 Personal Account 接口")
@RestController
@RequestMapping(value = "/personal/account")
public class PersonalAccountController {

    @Autowired
    private AccountService accountService;

    @ApiOperation(value="根据用户编号获取用户姓名", notes="test: 仅1和2有正确返回")
    @PostMapping(value = "/signUp/{type}")
    public RequestState signUp(@PathVariable(value = "type") int type,
                               @RequestParam(value = "username") String username,
                               @RequestParam(value = "password")String password){

        Date date = new Date(new java.util.Date().getTime());

        User user = new User();

        user.setUsername(username);
        user.setPassword(password);
        user.setCreatedAt(date);
        user.setLastSignInAt(date);
        user.setUpdatedAt(date);

        //判断注册类型 调用不同的注册方法
        return type == 1 ? this.useUsernameAndPasswordToSignUp(user) : this.SMSVerificationRegistration(user);
    }

    private RequestState useUsernameAndPasswordToSignUp(User user){
        return this.accountService.useUsernameAndPasswordToSignUp(user);
    }

    private RequestState SMSVerificationRegistration(User user){
        return null;
    }
}
