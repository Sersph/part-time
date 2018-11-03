package com.tidc.parttimemonarch.controller;

import com.tidc.parttimemonarch.domain.PageResultBean;
import com.tidc.parttimemonarch.domain.PartTime;
import com.tidc.parttimemonarch.domain.SelectPartTimeCondition;
import com.tidc.parttimemonarch.mapper.PartTimeMapper;
import com.tidc.parttimemonarch.service.PartTimeService;
import com.tidc.parttimemonarch.vo.PartTimeInfoRequestResult;
import com.tidc.parttimemonarch.vo.PartTimeVO;
import com.tidc.parttimemonarch.vo.RequestResult;
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
    public PartTimeInfoRequestResult getBaseInfo(){
        return partTimeService.getBaseInfo();
    }


    @ApiModelProperty(value = "添加兼职")
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
    @ApiModelProperty(value = "搜索兼职数据")
    @GetMapping(value = "/getPartTime")
    public PageResultBean<PartTimeVO> getPartTimeList(@Valid SelectPartTimeCondition selectPartTimeCondition){
        return this.partTimeService.getPartTimeList(selectPartTimeCondition);
    }


    /**
     * 根据id获取单个兼职详情
     * @param id
     * @return
     */
    @ApiModelProperty(value = "根据id获取单个兼职详情")
    @GetMapping(value = "/getPartTime/{id}")
    public PartTimeVO getPartTimeById(@PathVariable(value = "id") int id){
        return this.partTimeService.getPartTimeById(id);
    }

}
