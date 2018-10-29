//package com.tidc.parttimemonarch.domain;
//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;
//import javax.validation.constraints.Size;
//
///**
// * @Author: 李家宝
// * @Date: 2018/10/29 12:04
// */
//@ApiModel("兼职信息表")
//@Data
//public class PartTime {
//
//    private int id;
//
//    @ApiModelProperty("标题")
//    private String name;
//
//    @ApiModelProperty("省份id")
//    @Pattern(regexp = "^{6,20}$", message = "省份id为六位数")
//    private String provinceId;
//
//    @ApiModelProperty("城市id")
//    @Pattern(regexp = "^{6,20}$", message = "城市id为六位数")
//    private String cityId;
//
//    @ApiModelProperty("地区id")
//    @Pattern(regexp = "^{6,20}$", message = "地区id为六位数")
//    private String areaId;
//
//    @ApiModelProperty("详细地址")
//    @Pattern(regexp = "^{1,50}$", message = "详细地址")
//    private String detailAddress;
//
//    @ApiModelProperty("招聘人数")
//    @Pattern(regexp = "^{1,11}$", message = "招聘人数")
//    private int recruitmentCont;
//
//    @ApiModelProperty("工资")
//    @NotNull(message = "工资不能为空")
//    private double price;
//
//    @ApiModelProperty("工资计算方式")
//    @NotNull(message = "工资计算方式不能为空")
//    private int calculationTypeId;
//
//    @ApiModelProperty("工资结算方式")
//    @NotNull(message = "工资结算方式不能为空")
//    private int settlementTypeId;
//
//    @ApiModelProperty("用户id")
//    private int userId;
//
//    @ApiModelProperty("工作描述")
//    @NotNull(message = "工作描述不能为空")
//    private String jobDescription;
//    `job_description` text NOT NULL COMMENT '工作描述',
//    `contact_people` varchar(30) NOT NULL DEFAULT '' COMMENT '联系人',
//    `contact_phone` bigint(19) NOT NULL DEFAULT '0' COMMENT '联系电话',
//    `working_date_start` date NOT NULL DEFAULT '2018-01-01' COMMENT '工作开始日期',
//    `working_date_end` date NOT NULL DEFAULT '2018-01-01' COMMENT '工作结束日期',
//    `working_time_week` varchar(255) NOT NULL DEFAULT '' COMMENT '工作时间 周几',
//    `working_time` varchar(255) NOT NULL DEFAULT '' COMMENT '上班时间',
//    `price_description` text COMMENT '薪资描述',
//    `part_time_type_id` int(11) NOT NULL DEFAULT '0' COMMENT '兼职类型',
//    `part_time_species_id` int(11) NOT NULL DEFAULT '0' COMMENT '兼职种类',
//
//}
