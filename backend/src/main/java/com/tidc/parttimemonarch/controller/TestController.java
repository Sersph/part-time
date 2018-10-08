package com.tidc.parttimemonarch.controller;

import com.tidc.parttimemonarch.enumerate.Code;
import com.tidc.parttimemonarch.result.RequestResult;
import com.tidc.parttimemonarch.util.RedisUtli;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "redis 测试")
@RestController
public class TestController {


    @ApiOperation(value = "添加一条数据进redis")
    @GetMapping(value = "/test")
    public RequestResult test(@RequestParam(value = "key")String key, @RequestParam(value = "value") String value){
        RedisUtli.set(key, value);
        return new RequestResult(Code.SUCCEED);
    }

    @ApiOperation(value = "从 redis 里获取到一条记录")
    @GetMapping(value = "/test2")
    public Object test2(@RequestParam(value = "key")String key){

        return RedisUtli.get(key);
    }


}
