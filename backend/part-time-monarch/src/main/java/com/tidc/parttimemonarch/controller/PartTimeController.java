package com.tidc.parttimemonarch.controller;

import com.tidc.parttimemonarch.domain.PartTime;
import com.tidc.parttimemonarch.service.PartTimeService;
import com.tidc.parttimemonarch.vo.PartTimeRequesResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author: 李家宝
 * @Date: 2018/10/28 21:52
 */
@Api(tags = "partTime")
@RestController
@RequestMapping(value = "/partTime")
public class PartTimeController {

    @Autowired
    private PartTimeService partTimeService;


    @ApiModelProperty(value = "获取兼职类型等数据")
    @GetMapping(value = "/baseInfo")
    public PartTimeRequesResult getBaseInfo(){
        return partTimeService.getBaseInfo();
    }


    @ApiModelProperty(value = "添加兼职")
    @PostMapping(value = "/addPartTime")
    public PartTime addPartTime(@Valid PartTime partTime){
        return partTime;
    }



}
