package com.tidc.parttimemonarch;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "一个用来测试swagger注解的控制器")
@RestController
public class Test {


    @ApiOperation(value = "测试api", notes = "test: return  hello swagger ")
    @GetMapping(value = "hello")
    public String hello(){
        System.out.println("haha");
        return "Hello swagger";
    }
}
