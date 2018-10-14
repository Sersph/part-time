package com.tidc.parttimemonarch.controller;

import com.tidc.parttimemonarch.pojo.EnterpriseUser;
import com.tidc.parttimemonarch.pojo.PersonalUser;
import com.tidc.parttimemonarch.service.AccountService;
import com.tidc.parttimemonarch.service.impl.EMailService;
import com.tidc.parttimemonarch.util.CookieUtil;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.UUID;

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
    public RequestResult getAccountInfo(){
        Object account = SessionUtil.getSessionAttribute(CookieUtil.getCookie().getValue());

        if (account!= null){
            this.result.succeed(account);
        }else {
            this.result.error(1003, "用户未登陆");
        }
        return this.result;
    }


    @ApiOperation(value = "退出登陆")
    @DeleteMapping(value = "/signOut")
    public RequestResult signOut(HttpServletResponse response){
        SessionUtil.removeSession(CookieUtil.getCookie().getValue());
        CookieUtil.removeCookie(response);
        this.result.succeed(null);
        return this.result;
    }


    @ApiOperation(value = "发送邮件验证码")
    @ApiImplicitParam(name = "email", value = "邮箱", required = true, dataType = "String", paramType = "query")
    @PostMapping(value = "/sendMailCaptcha")
    public RequestResult sendMailCaptcha(@RequestParam(value = "email") String email){
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
            @ApiImplicitParam(name = "captcha", value = "验证码", required = true, dataType = "String", paramType = "query"),
    })
    @PostMapping(value = "enterprise/signUp")
    public RequestResult enterpriseSignUp(@Valid EnterpriseUser enterpriseUser, @RequestParam(value = "captcha") String captcha, HttpServletResponse response){
        enterpriseUser = this.accountService.enterpriseSignUp(enterpriseUser, captcha);
        this.login(response, enterpriseUser);
        this.result.succeed();
        return this.result;
    }






    @ApiOperation(value="企业用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "email", value = "email", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping(value = "enterprise/signIn")
    public RequestResult enterpriseSignIn(@Valid EnterpriseUser enterpriseUser, HttpServletResponse response){

        enterpriseUser = this.accountService.enterpriseSignIn(enterpriseUser);
        this.login(response, enterpriseUser);
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
    public RequestResult personalSignUp(@Valid PersonalUser personalUser, HttpServletResponse response){

        personalUser = accountService.personalSignUp(personalUser);
        this.login(response, personalUser);
        this.result.succeed();
        return this.result;
    }





    @ApiOperation(value="普通用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query"),
    })
    @PostMapping(value = "/personal/signIn")
    public RequestResult personalSignIn(@Valid PersonalUser personalUser, HttpServletResponse response){
        PersonalUser personalUser1 = accountService.personalSignIn(personalUser);
        this.login(response, personalUser);
        this.result.succeed();
        return this.result;
    }


    private void login(HttpServletResponse response, Object value){
        String key = UUID.randomUUID().toString();

        CookieUtil.addCookie(key, response);

        SessionUtil.addSession(key, value);
    }
}




























