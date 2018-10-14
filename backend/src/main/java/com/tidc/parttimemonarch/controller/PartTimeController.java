package com.tidc.parttimemonarch.controller;

import com.tidc.parttimemonarch.dao.PartTimeDAO;
import com.tidc.parttimemonarch.pojo.EnterpriseUser;
import com.tidc.parttimemonarch.pojo.PartTime;
import com.tidc.parttimemonarch.service.PartTimeService;
import com.tidc.parttimemonarch.vo.PartTimeResult;
import com.tidc.parttimemonarch.vo.RequestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 兼职 Controller
 *
 * @Author: 李家宝
 * @Date: 2018/10/12 17:00
 */

@Api(tags = "兼职接口")
@RestController
@RequestMapping(value = "/partTime")
public class PartTimeController {

    @Autowired
    private PartTimeResult result;

    @Autowired
    private PartTimeDAO partTimeDAO;

    @Autowired
    private PartTimeService partTimeService;


    /**
     * 获取兼职种类，兼职类型，结算类型，工资结算方式
     *
     * @return RequestResult
     */

    @ApiOperation(value = "获取兼职种类，兼职类型，结算类型，工资结算方式")
    @GetMapping(value = "/baseInfo")
    public RequestResult baseInfo(){
        this.result.setPartTimeTypes(this.partTimeDAO.selectPartTimeTypes());
        this.result.setPartTimeSpecies(this.partTimeDAO.selectPartTimeSpecies());
        this.result.setSettlementTypes(this.partTimeDAO.selectSettlementType());
        this.result.setCalculationTypes(this.partTimeDAO.selectCalculationType());
        this.result.succeed();
        return this.result;
    }


    /**
     * 发布兼职
     *
     * @param partTime
     * @param httpServletRequest
     * @return RequestResult
     */

    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "兼职标题", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "provinceId", value = "省份id", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "cityId", value = "城市id", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "areaId", value = "地区id", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "detailAddress", value = "详细地址", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "recruitmentCont", value = "招聘人数", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "price", value = "工资", required = true, dataType = "float", paramType = "query"),
            @ApiImplicitParam(name = "calculationTypeId", value = "工资计算方式", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "settlementTypeId", value = "工资结算方式", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "jobDescription", value = "工作描述", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "contactPeople", value = "联系人", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "contactPhone", value = "联系电话", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "workingDateStart", value = "工作开始日期", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "workingDateEnd", value = "工作结束日期", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "workingTimeWeek", value = "工作时间 周几 到 周几", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "workingTime", value = "上班时间", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "priceDescription", value = "薪资描述", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "partTimeTypeId", value = "兼职类型", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "partTimeSpeciesId", value = "兼职种类", required = true, dataType = "String", paramType = "query")
    })
    @ApiOperation(value = "发布兼职")
    @PostMapping(value = "/partTime")
    public RequestResult addPartTime(@Valid PartTime partTime, HttpServletRequest httpServletRequest){

        EnterpriseUser user = (EnterpriseUser) httpServletRequest.getSession().getAttribute("user");

        partTime.setEnterpriseUserId(user.getId());
        this.partTimeService.addPartTime(partTime);

        this.result.succeed();
        return this.result;
    }



}
