package com.tidc.part.time.monarch.controller;

import com.tidc.part.time.monarch.domain.PageResultBean;
import com.tidc.part.time.monarch.domain.PartTime;
import com.tidc.part.time.monarch.domain.PartTimeApply;
import com.tidc.part.time.monarch.domain.SelectPartTimeCondition;
import com.tidc.part.time.monarch.service.PartTimeService;
import com.tidc.part.time.monarch.vo.PartTimeInfoRequestResult;
import com.tidc.part.time.monarch.vo.PartTimeVO;
import com.tidc.part.time.monarch.vo.RequestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: 李家宝
 * @Date: 2018/10/28 21:52
 */
@Api(tags = "兼职接口")
@RestController
@RequestMapping(value = "/partTime")
public class PartTimeController {

    @Autowired
    private PartTimeService partTimeService;

    @ApiOperation(value = "获取兼职类型等数据")
    @GetMapping(value = "/baseInfo")
    public PartTimeInfoRequestResult getBaseInfo(){
        return partTimeService.getBaseInfo();
    }


    @ApiOperation(value = "添加兼职")
    @PostMapping(value = "/addPartTime")
    @PreAuthorize(value = "hasAnyAuthority('enterprise')")
    public RequestResult addPartTime(@Valid PartTime partTime, @RequestParam(value = "access_token") String token){
        this.partTimeService.addPartTime(partTime, token);
        return new RequestResult().succeed();
    }



    /**
     * 首页获取兼数据的接口
     * 按照页码返回数据
     * @return
     */
    @ApiOperation(value = "搜索兼职数据")
    @GetMapping(value = "/getPartTime")
    public PageResultBean<PartTimeVO> getPartTimeList(@Valid SelectPartTimeCondition selectPartTimeCondition){
        return this.partTimeService.getPartTimeList(selectPartTimeCondition);
    }


    /**
     * 根据id获取单个兼职详情
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id获取单个兼职详情")
    @GetMapping(value = "/getPartTime/{id}")
    public PartTimeVO getPartTimeById(@PathVariable(value = "id") int id){
        return this.partTimeService.getPartTimeById(id);
    }


    @ApiOperation(value = "申请兼职")
    @PostMapping(value = "/applyPartTime")
    @PreAuthorize(value = "hasAnyAuthority('personal')")
    public RequestResult applyPartTime(@Valid PartTimeApply partTimeApply, @RequestParam(value = "access_token") String token){
        return this.partTimeService.applyPartTime(partTimeApply, token);
    }


}




























