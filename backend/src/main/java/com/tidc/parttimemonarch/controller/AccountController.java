package com.tidc.parttimemonarch.controller;

import com.tidc.parttimemonarch.pojo.EnterpriseUser;
import com.tidc.parttimemonarch.pojo.PersonalUser;
import com.tidc.parttimemonarch.service.AccountService;
import com.tidc.parttimemonarch.service.impl.EMailService;
import com.tidc.parttimemonarch.util.SessionUtil;
import com.tidc.parttimemonarch.vo.AccountRequestResult;
import com.tidc.parttimemonarch.vo.RequestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Api(tags = "用户接口")
@RestController
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private EMailService eMailService;

    @Autowired
    private AccountRequestResult result;








    @ApiOperation(value="获取用户信息")
    @GetMapping(value = "/accountInfo")
    public RequestResult getAccountInfo(HttpServletRequest httpServletRequest){

        HttpSession session = httpServletRequest.getSession();

        if (!SessionUtil.isSession("user", session)){
            this.result.error(1003, "用户未登陆");
        }else{
            this.result.succeed(session.getAttribute("user"));
        }


        return this.result;
    }


    @ApiOperation(value = "退出登陆")
    @DeleteMapping(value = "/signOut")
    public RequestResult signOut(HttpServletRequest httpServletRequest){
        if (!SessionUtil.removeSession("user", httpServletRequest.getSession())){
            this.result.error(1, "失败");
        }else{
            this.result.succeed(null);
        }

        return this.result;
    }


    @ApiOperation(value = "发送邮件验证码")
    @ApiImplicitParam(name = "email", value = "邮箱", required = true, dataType = "String", paramType = "header")
    @PostMapping(value = "/sendMailCaptcha")
    public RequestResult sendMailCaptcha(@RequestHeader(value = "email") String email){
        this.eMailService.sendMailCaptcha(email);
        this.result.succeed();
        return this.result;
    }





    /**
     *  企业用户注册登陆接口
     *
     * @Author: 李家宝
    */

    @ApiOperation(value="企业用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "enterpriseName", value = "企业名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "email", value = "email", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "cityId", value = "企业所在城市", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "captcha", value = "验证码", required = true, dataType = "String", paramType = "header"),
    })
    @PostMapping(value = "enterprise/signUp")
    public RequestResult enterpriseSignUp(@Valid EnterpriseUser enterpriseUser, @RequestHeader(value = "captcha") String captcha, HttpServletRequest httpServletRequest){
        enterpriseUser = this.accountService.enterpriseSignUp(enterpriseUser, captcha);
        SessionUtil.signUpSession(enterpriseUser, httpServletRequest.getSession());
        this.result.succeed();
        return this.result;
    }






    @ApiOperation(value="企业用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "email", value = "email", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping(value = "enterprise/signIn")
    public RequestResult enterpriseSignIn(@Valid EnterpriseUser enterpriseUser, HttpServletRequest httpServletRequest){

        enterpriseUser = this.accountService.enterpriseSignIn(enterpriseUser);
        SessionUtil.signUpSession(enterpriseUser, httpServletRequest.getSession());
        this.result.succeed();
        return this.result;
    }





















    /**
     * 普通用户注册登陆接口
     *
     * 注册 sign up
     *
     * 登陆 sign in
     *
     *
     * @Author: 李家宝
     * @Date: ${DATE} ${TIME}
    */


    @ApiOperation(value="普通用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping(value = "/personal/signUp")
    public RequestResult personalSignUp(@Valid PersonalUser personalUser, HttpServletRequest httpServletRequest){

        personalUser = accountService.personalSignUp(personalUser);

        SessionUtil.signUpSession(personalUser, httpServletRequest.getSession());

        this.result.succeed();
        return this.result;
    }





    @ApiOperation(value="普通用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query"),
    })
    @PostMapping(value = "/personal/signIn")
    public RequestResult personalSignIn(@Valid PersonalUser personalUser, HttpServletRequest httpServletRequest){

        SessionUtil.signUpSession(accountService.personalSignIn(personalUser), httpServletRequest.getSession());

        this.result.succeed();
        return this.result;
    }
}




























