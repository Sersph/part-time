package com.tidc.parttimemonarch.controller;

import com.tidc.parttimemonarch.enumerate.Code;
import com.tidc.parttimemonarch.result.RequestResult;
import com.tidc.parttimemonarch.service.IAccountService;
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
    private IAccountService accountService;


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
}
