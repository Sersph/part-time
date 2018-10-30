package com.tidc.parttimemonarch.controller;

import com.tidc.parttimemonarch.domain.PartTime;
import com.tidc.parttimemonarch.service.PartTimeService;
import com.tidc.parttimemonarch.vo.PartTimeRequesResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize(value = "hasAnyAuthority('enterprise')")
    public PartTime addPartTime(@Valid PartTime partTime, @RequestParam(value = "access_token") String token){

        return partTime;
    }



}
