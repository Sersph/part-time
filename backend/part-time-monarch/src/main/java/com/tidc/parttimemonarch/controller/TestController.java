//package com.tidc.parttimemonarch.controller;
//
//
//import com.tidc.parttimemonarch.service.impl.EmailServiceImpl;
//import com.tidc.parttimemonarch.vo.RequestResult;
//import io.swagger.annotations.Api;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.Valid;
//
//@Api(tags = "测试用接口")
//@RestController
//@RequestMapping(value = "/test")
//public class TestController {
//
//    @Autowired
//    private EmailServiceImpl eMailService;
//
//    @Autowired
//    @Qualifier("requestResult")
//    private RequestResult result;
//
//
//
//
//
//    @GetMapping(value = "/getTest")
//    public Object getTest(@Valid Test test){
//        return test;
//    }
//}
