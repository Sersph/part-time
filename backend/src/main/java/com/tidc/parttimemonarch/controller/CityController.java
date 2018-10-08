package com.tidc.parttimemonarch.controller;

import com.tidc.parttimemonarch.result.RequestResult;
import com.tidc.parttimemonarch.service.city.ICityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "获取城市数据")
@RestController
@RequestMapping(value = "/common/location")
public class CityController {

    @Autowired
    private ICityService cityService;




    @ApiOperation(value = "获取城市数据")
    @GetMapping(value = "/regionList")
    public RequestResult obtainCity(){
        return this.cityService.obtainCity();
    }
}