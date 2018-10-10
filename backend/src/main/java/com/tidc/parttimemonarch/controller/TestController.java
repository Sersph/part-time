package com.tidc.parttimemonarch.controller;


import com.tidc.parttimemonarch.result.RequestResult;
import com.tidc.parttimemonarch.service.impl.EMailService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试用接口")
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private EMailService eMailService;

    @Autowired
    private RequestResult result;


//    @GetMapping(value = "/getTest")
//    public Object getTest(@RequestParam(value = "email") String email){
////        System.out.println();
//
//
//        return ;
//
////        return this.eMailService.getVerificationCode(email);
//    }
}
