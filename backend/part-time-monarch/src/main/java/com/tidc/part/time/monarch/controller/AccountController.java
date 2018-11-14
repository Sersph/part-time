package com.tidc.part.time.monarch.controller;

import com.tidc.part.time.monarch.domain.User;
import com.tidc.part.time.monarch.domain.UserInfo;
import com.tidc.part.time.monarch.exception.ResultException;
import com.tidc.part.time.monarch.security.UserTokenDTO;
import com.tidc.part.time.monarch.service.AccountService;
import com.tidc.part.time.monarch.service.EmailService;
import com.tidc.part.time.monarch.verify.EnterpriseVerifyUserInfo;
import com.tidc.part.time.monarch.vo.RequestResult;
import com.tidc.part.time.monarch.vo.UserInfoRequestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 用户 controller
 * @author 李家宝
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private EmailService eMailService;

    /**
     * 使用 token 获取用户信息
     * 不带 token 过来视为未登录
     * @return
     */
    @ApiOperation(value="获取用户信息")
    @GetMapping(value = "/accountInfo")
    public RequestResult getAccountInfo(@RequestParam(value = "access_token", defaultValue = "") String token){

        if (token.equals("")){
            throw new ResultException(1002, "用户未登陆");
        }
        UserInfo accountInfo = this.accountService.getAccountInfo(token);

        return new UserInfoRequestResult().succeed(accountInfo);
    }

    /**
     * 退出登陆
     * @param token
     * @return
     */

    @ApiOperation(value = "退出登陆")
    @DeleteMapping(value = "/signOut")
    @PreAuthorize(value = "hasAnyAuthority('root','enterprise', 'personal')")
    public RequestResult signOut(@RequestParam(value = "access_token") String token){
        this.accountService.logout(token);
        return new RequestResult().succeed();
    }


    /**
     * 发送邮箱验证码
     * @param email
     * @return
     */
    @ApiOperation(value = "发送邮件验证码")
    @ApiImplicitParam(name = "email", value = "邮箱", required = true, dataType = "String", paramType = "query")
    @PostMapping(value = "/sendMailCaptcha")
    public RequestResult sendMailCaptcha(@RequestParam(value = "email") String email){
        this.eMailService.sendEnterpriseSignUpMainCode(email);
        return new RequestResult().succeed();
    }



    /**
     *  企业用户注册登陆接口
     *
     * @Author: 李家宝
    */

    @ApiOperation(value="企业用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "企业名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "username", value = "用户名 用户名和邮箱相同", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "email", value = "email", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "cityId", value = "企业所在城市", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "captcha", value = "验证码", required = true, dataType = "String", paramType = "query"),
    })
    @PostMapping(value = "enterprise/signUp")
    public RequestResult enterpriseSignUp(@Validated(value = EnterpriseVerifyUserInfo.class) UserInfo userInfo,
                                          @Validated(value = EnterpriseVerifyUserInfo.class) User user,
                                          @RequestParam(value = "captcha") String captcha){


        this.accountService.enterpriseSignUp(userInfo, user, captcha);

        return new RequestResult().succeed();
    }


    /**
     * 普通用户注册接口
     *
     * @Author: 李家宝
    */
    @ApiOperation(value="普通用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query")
    })

    @PostMapping(value = "/personal/signUp")
    public RequestResult personalSignUp(@Valid User user){
        this.accountService.personalSignUp(user);
        this.accountService.signIn(user);
        return new RequestResult().succeed();
    }


    /**
     * 统一用户登陆接口
     * @param user
     * @return
     */

    @ApiOperation(value="用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query"),
    })
    @PostMapping(value = "/signIn")
    public UserTokenDTO signIn(@Valid User user){
        return this.accountService.signIn(user);
    }
}