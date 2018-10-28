package com.tidc.parttimemonarch.controller;


import com.tidc.parttimemonarch.service.impl.EMailService;
import com.tidc.parttimemonarch.vo.RequestResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试用接口")
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private EMailService eMailService;

    @Autowired
    @Qualifier("requestResult")
    private RequestResult result;



//    @ApiOperation(value="普通用户注册")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "enterpriseName", value = "企业名称", required = true, dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "email", value = "email", required = true, dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "cityId", value = "企业所在城市", required = true, dataType = "int", paramType = "query")
//    })
//    @PostMapping(value = "/test")
//    public Object test(@Valid EnterpriseUser enterpriseUser){
//
//        return enterpriseUser;
//    }
//
////    @GetMapping(value = "/getTest")
////    public Object getTest(@RequestParam(value = "email") String email){
//////        System.out.println();
////
////
////        return ;
////
//////        return this.eMailService.getVerificationCode(email);
////    }
}
