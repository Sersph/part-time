package com.tidc.parttimemonarch.controller;

import com.tidc.parttimemonarch.enumerate.Code;
import com.tidc.parttimemonarch.message.RequestState;
import com.tidc.parttimemonarch.service.account.AccountStateService;
import com.tidc.parttimemonarch.util.SessionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(value = "用户状态接口")
@RestController
@RequestMapping(value = "/account")
public class AccountStateController {


    @Autowired
    private AccountStateService accountStateService;


    @ApiOperation(value="获取用户是否登陆")
    @GetMapping(value = "/isSingIn")
    public RequestState isTheUserLoggedIn(HttpServletRequest httpServletRequest){
        return this.accountStateService.isTheUserLoggedIn(httpServletRequest.getSession());
    }


    @ApiOperation(value = "退出登陆")
    @GetMapping(value = "/exit")
    public RequestState exit(HttpServletRequest httpServletRequest){
        if (SessionUtil.removeSession(httpServletRequest.getSession())){
            return new RequestState(Code.SUCCEED);
        }

        return new RequestState(Code.ERROR);
    }
}
